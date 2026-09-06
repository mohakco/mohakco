<!-- Generated from README.gtpl by .github/workflows/markscribe.yml. Edit the template, not this file. -->

### Mohak Malhotra

> Just trying to mess with the compiler.

I work on **Rust, build systems, and infrastructure**. I'm an SDE intern at
[Aftershoot](https://github.com/aftershootco), working on the tooling that builds, tests, and ships
our desktop app across Windows and macOS. Outside work, I'm exploring low-level systems and making
products around developer tooling

[`email`](mailto:mohakmalhotra021@gmail.com)
[`@entropeous`](https://x.com/entropeous)

### What I'm working on

Anything and everything related to **Build tooling** — Cargo, Rust FFI, native C/C++ dependencies,
and cross-platform toolchains.

### Selected projects

- [**simulatr**](https://github.com/mohakco/simulatr) — a deterministic, headless ESP32 simulator in Rust, with an ELF loader, an Xtensa instruction-subset interpreter, and UART emulation. In progress.
- [**kernel**](https://github.com/mohakco/kernel) — a minimal Rust OS kernel with BIOS and UEFI boot support, interrupt handling, and basic graphics.
- [**GitHub Project Assistant**](https://github.com/Optimus-Labs/github-project-assistant) — a Python CLI for AI-assisted PR descriptions, commit messages, code reviews, and repository maintenance. Built for our first-place entry at the AIMS-DTU BrAInwave Hackathon.

### Tools I reach for

**Languages:** Rust, Go, C, Python, TypeScript, Bash, Nushell
**Build & systems:** Cargo, vcpkg, Clang, CMake, Nix, Linux
**Infrastructure:** GitHub Actions, GCP, Docker, Kubernetes, Proxmox, Tart, QEMU
{{ with recentPullRequests 5 }}
### Recent pull requests
{{ range . }}
- [{{ .Title }}]({{ .URL }}) on [{{ .Repo.Name }}]({{ .Repo.URL }})
{{- end }}
{{ end }}{{ with recentStars 5 }}
### Recently starred
{{ range . }}
- [{{ .Repo.Name }}]({{ .Repo.URL }}){{ with .Repo.Description }} — {{ . }}{{ end }}
{{- end }}
{{ end }}
