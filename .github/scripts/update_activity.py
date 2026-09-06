#!/usr/bin/env python3
"""Refresh the activity sections of the profile README.

Reads only public data, so the workflow's built-in GITHUB_TOKEN is enough:
no personal access token is involved. markscribe needed one because it asked
GraphQL for pull requests, which a fine-grained token cannot read and which a
classic token cannot reach inside orgs that forbid classic tokens.
"""

from __future__ import annotations

import json
import os
import re
import sys
import urllib.error
import urllib.parse
import urllib.request

USER = "mohakco"
README = "README.md"
LIMIT = 5
API = "https://api.github.com"


def get(path: str) -> object:
    request = urllib.request.Request(
        f"{API}{path}",
        headers={
            "Accept": "application/vnd.github+json",
            "X-GitHub-Api-Version": "2022-11-28",
            "User-Agent": f"{USER}-profile-readme",
        },
    )
    token = os.environ.get("GITHUB_TOKEN")
    if token:
        request.add_header("Authorization", f"Bearer {token}")
    with urllib.request.urlopen(request, timeout=30) as response:
        return json.load(response)


def escape(text: str) -> str:
    """Neutralize markdown control characters coming from other people's text."""
    return re.sub(r"([\\`*_\[\]<>|])", r"\\\1", text.strip())


def pull_requests() -> list[str]:
    query = urllib.parse.quote(f"type:pr author:{USER}")
    found = get(f"/search/issues?q={query}&sort=created&order=desc&per_page={LIMIT}")
    lines = []
    for item in found["items"]:
        repo = item["repository_url"].split("/repos/", 1)[1]
        lines.append(
            f"- [{escape(item['title'])}]({item['html_url']}) "
            f"on [{escape(repo)}](https://github.com/{repo})"
        )
    return lines


def stars() -> list[str]:
    starred = get(f"/users/{USER}/starred?per_page={LIMIT}")
    lines = []
    for repo in starred:
        line = f"- [{escape(repo['full_name'])}]({repo['html_url']})"
        if repo.get("description"):
            line += f" — {escape(repo['description'])}"
        lines.append(line)
    return lines


def section(marker: str, heading: str, lines: list[str], blurb: str = "") -> str:
    """Render a whole section, or nothing at all when there is no activity."""
    if not lines:
        return f"<!-- {marker} -->\n<!-- /{marker} -->"
    body = f"## {heading}\n\n"
    if blurb:
        body += f"{blurb}\n\n"
    body += "\n".join(lines)
    return f"<!-- {marker} -->\n\n{body}\n\n<!-- /{marker} -->"


def replace(readme: str, marker: str, rendered: str) -> str:
    pattern = re.compile(
        rf"<!-- {re.escape(marker)} -->.*?<!-- /{re.escape(marker)} -->",
        re.DOTALL,
    )
    if not pattern.search(readme):
        sys.exit(f"{README} is missing the '{marker}' markers")
    return pattern.sub(lambda _: rendered, readme, count=1)


def main() -> None:
    try:
        pr_lines = pull_requests()
        star_lines = stars()
    except urllib.error.HTTPError as error:
        sys.exit(f"GitHub API request failed: {error.code} {error.reason}")

    with open(README, encoding="utf-8") as handle:
        readme = handle.read()

    updated = replace(
        readme,
        "ACTIVITY:pull-requests",
        section("ACTIVITY:pull-requests", "🔀 Recent pull requests", pr_lines),
    )
    updated = replace(
        updated,
        "ACTIVITY:stars",
        section(
            "ACTIVITY:stars",
            "⭐ Recently starred",
            star_lines,
            "A few things that caught my eye.",
        ),
    )

    if updated == readme:
        print("Activity sections already up to date.")
        return

    with open(README, "w", encoding="utf-8") as handle:
        handle.write(updated)
    print(f"Updated {len(pr_lines)} pull requests and {len(star_lines)} starred repos.")


if __name__ == "__main__":
    main()
