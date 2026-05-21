---
name: ingest
description: Ingest a new source document into the wiki. Reads a raw source file, discusses key takeaways, creates summary and concept pages, and updates the index.
user_invocable: true
argument-hint: "[path-to-raw-source]"
---

# Ingest Source Document

Ingest a new source document into the Android XR knowledge wiki.

## Steps

1. **Read the source** at `$ARGUMENTS` in `raw/`
2. **Discuss key takeaways** — summarize the 3-5 most important points for the user
3. **Create a source summary** at `wiki/sources/source-<name>.md` with frontmatter:
   ```yaml
   ---
   title: "Source: <document name>"
   type: source-summary
   sources:
     - <path-to-raw-file>
   created: <today>
   updated: <today>
   confidence: high
   ---
   ```
4. **Update or create concept/entity/workflow pages** as needed — check existing pages first to avoid duplicates
5. **Update `wiki/index.md`** with any new entries
6. **Append to `wiki/log.md`** with the date, operation, source, and affected pages

## Conventions

- Filenames: kebab-case (e.g., `context-window.md`)
- Cross-references: use `[[wikilinks]]`
- Source citations: link back to `raw/` paths
- Every page must have YAML frontmatter with type, sources, related, created, updated, confidence
