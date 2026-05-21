# Android XR Development Wiki

**Always check wiki/ before answering.** Read `wiki/index.md` first, then relevant pages. Why: the wiki has verified info from official Android XR docs, Jetpack XR SDK references, and grant program details.

## Structure

- `raw/` — Immutable sources. **Never modify.** Why: raw files are the verification baseline for all wiki claims.
- `wiki/` — LLM-maintained pages. `index.md` (catalog), `hot.md` (session cache), `log.md` (append-only).
- `outputs/` — Lint reports, handoffs, grant application drafts.

## Page Frontmatter (required)

```yaml
---
title: Page Title
type: concept | entity | source-summary | comparison | workflow | how-to
tldr: "Under 50 chars"
sources: [raw/docs/file.md]
related: ["[[page]]"]
created: YYYY-MM-DD
updated: YYYY-MM-DD
confidence: high | medium | low
last_verified: YYYY-MM-DD
aliases: []
---
```

## Rules

- Filenames: kebab-case. Links: `[[wikilinks]]`. Sources: trace to `raw/`.
- **Never overwrite contradictions** — mark with `> [!contradiction]` callout. Why: premature resolution destroys signal.
- **Never compress Read/cat output** — the output IS the content.
- **Never delete wiki pages** — mark `status: deprecated`.
- **Check `aliases` before creating pages.** Why: duplicates fragment knowledge and drift apart.

## Workflows

**Query:** index.md → relevant pages → synthesize with `[[wikilink]]` citations → file novel findings as new pages (anti-evaporation).

**Ingest:** Read raw/ source → discuss takeaways → create `wiki/sources/` summary → update concept/entity pages → update index.md → append log.md → update hot.md.

**Lint:** Run `./scripts/wiki-lint.sh`. 13 checks, <3s, zero LLM cost. Score: Structure 40 + Content 20 + Metadata 20 + Freshness 20 = 100.

## hot.md Format

Emoji-coded, under 120 chars each, cap 30 entries:
`✅ completed | 📝 created | 🔧 fixed | ⚠️ gotcha | 🔄 in-progress | ❌ blocked | 💡 decision`
