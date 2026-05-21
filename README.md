# Android XR Development Wiki

A knowledge base for Android XR development using the Jetpack XR SDK, built on the [Karpathy LLM Wiki pattern](https://blog.starmorph.com/blog/karpathy-llm-wiki-knowledge-base-guide). Clone it, `cd` into it, run `claude`, and you have an AI assistant that knows the Android XR ecosystem.

## Quick Start

```bash
git clone <repo-url>
cd android-xr
claude
```

## What's In Here

**19 wiki pages** covering the Jetpack XR SDK, device types, development workflows, and the Android XR Developer Catalyst Program.

| Category | Pages | Coverage |
|----------|-------|----------|
| **Concepts** | 8 | Jetpack XR SDK, Compose for XR, SceneCore, ARCore, Glimmer, Projected, Spatial UI, Device Types |
| **Entities** | 4 | Catalyst Program, Android Studio XR, XREAL Project Aura, Google Play XR |
| **Workflows** | 4 | Getting started, grant application, immersive dev, augmented dev |
| **How-To** | 2 | Studio setup, project creation |
| **Comparisons** | 1 | Headsets vs wired glasses vs display glasses |
| **Sources** | 2 | Jetpack XR SDK overview, Catalyst Program details |

## Key Dates

- **2026-06-30** — Catalyst Program application deadline
- **2026-07-15** — Selection notification

## Skills

| Skill | What It Does |
|-------|-------------|
| `/wiki-query [question]` | Answer an Android XR question from the wiki |
| `/ingest [path]` | Add a new source document to the wiki |
| `/wiki-lint` | Run 13 automated quality checks |
| `/autoresearch` | Autonomous wiki quality improvement loop |
| `/freshness-check` | Delta-based staleness scan |
| `/handoff` | Session continuity document |
| `/write-a-skill` | Create new skills |

## Project Structure

```
android-xr/
├── CLAUDE.md              # Schema — turns Claude into an Android XR assistant
├── program.md             # AutoResearch loop definition
├── raw/docs/              # Immutable source documents
├── wiki/                  # Knowledge pages (19 pages)
├── scripts/               # wiki-lint.sh, find-contradictions.sh, compact-tool-output.sh
├── outputs/               # Lint reports, handoffs
└── .claude/skills/        # 7 skills + SKILL-TEMPLATE.md
```
