{{/* Edit this template; .github/workflows/action.yml generates README.md. */ -}}
<h1 align="center">Hey, I'm Mohak 👋</h1>

<p align="center">
  <strong>Rust · Build systems · Infrastructure</strong><br>
  <em>Just trying to mess with the compiler.</em>
</p>

<p align="center">
  <a href="mailto:mohakmalhotra021@gmail.com"><code>✉️ Email</code></a> &nbsp;·&nbsp;
  <a href="https://x.com/entropeous"><code>𝕏 @entropeous</code></a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Rust-CE422B?style=flat-square&amp;logo=rust&amp;logoColor=white" alt="Rust">
  <img src="https://img.shields.io/badge/Go-00ADD8?style=flat-square&amp;logo=go&amp;logoColor=white" alt="Go">
  <img src="https://img.shields.io/badge/Linux-FCC624?style=flat-square&amp;logo=linux&amp;logoColor=black" alt="Linux">
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=flat-square&amp;logo=githubactions&amp;logoColor=white" alt="GitHub Actions">
</p>

---

## 🧑‍💻 A little about me

I'm an **SDE intern at [Aftershoot](https://github.com/aftershootco)**, working on the tooling that builds, tests, and ships our production app.

Outside work, I'm exploring **low-level systems** and making products around **developer tooling**.

### 🔧 What I'm working on

Anything and everything related to **build tooling** — Cargo, Rust FFI, native C/C++ dependencies, and cross-platform toolchains.

## 🚀 Selected projects

### 🔬 [simulatr](https://github.com/mohakco/simulatr)

A deterministic, headless **ESP32 simulator** in Rust. An ELF loader, an Xtensa instruction-subset interpreter, and UART emulation — built for exploring firmware without the board.

`Rust` `Xtensa` `ELF` · 🚧 In progress

### 🦀 [kernel](https://github.com/mohakco/kernel)

A minimal **Rust OS kernel**, from BIOS and UEFI boot to interrupt handling and basic graphics.

`Rust` `UEFI` `BIOS`

### 🤖 [GitHub Project Assistant](https://github.com/Optimus-Labs/github-project-assistant)

A CLI for **AI-assisted repository maintenance**: PR descriptions, commit messages, code reviews, and more.

`Python` `Groq API` `Typer` · 🏆 First place, AIMS-DTU BrAInwave Hackathon

## 🧰 Tools I reach for

| | Toolkit |
| :--- | :--- |
| **💻 Languages** | Rust · Go · C · Python · TypeScript · Bash · Nushell |
| **⚙️ Build & systems** | Cargo · vcpkg · Clang · CMake · Nix · Linux |
| **☁️ Infrastructure** | GitHub Actions · GCP · Docker · Kubernetes · Proxmox · Tart · QEMU |
{{ with recentPullRequests 5 }}
## 🔀 Recent pull requests
{{ range . }}
- [{{ .Title }}]({{ .URL }}) on [{{ .Repo.Name }}]({{ .Repo.URL }})
{{- end }}
{{ end }}{{ with recentStars 5 }}
## ⭐ Recently starred

A few things that caught my eye.
{{ range . }}
- [{{ .Repo.Name }}]({{ .Repo.URL }}){{ with .Repo.Description }} — {{ . }}{{ end }}
{{- end }}
{{ end }}