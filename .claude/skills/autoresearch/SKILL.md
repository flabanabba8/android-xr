---
name: autoresearch
description: Run the autonomous wiki quality improvement loop. Finds weakest pages, scores content 0-10 against rubric, fixes lowest criteria, verifies structural lint stays at 100. Use when wiki needs quality improvement or user says "improve the wiki."
user_invocable: true
disable-model-invocation: true
argument-hint: optional page name to target specifically
---

# AutoResearch: Wiki Quality Loop

Autonomous loop that finds weak pages, scores them, and improves the lowest-scoring criteria.

<what-to-do>

1. Read `program.md` for the full rubric and loop definition
2. Run `./scripts/wiki-lint.sh --json` for baseline structural score
3. Find weakest page by content score (see Scoring Rubric below)
4. Score the page on all 6 criteria
5. Fix the lowest-scoring criterion
6. Re-score — verify improvement, verify lint didn't drop
7. Keep or revert, move to next page
8. After 10 pages or 30 minutes, report results
9. Log to `wiki/hot.md` using compressed observation format

</what-to-do>

<supporting-info>

## Scoring Rubric (0-10 per criterion)

| Criterion | What to evaluate |
|-----------|-----------------|
| **Completeness** | Does it cover the topic thoroughly? |
| **Accuracy** | Are claims correct and traceable to sources? |
| **Freshness** | Is `last_verified` within 90 days? Sources current? |
| **Structure** | Proper frontmatter, wikilinks, code blocks, sections? |
| **Usefulness** | Would this help someone solve a real problem? |
| **Conciseness** | No filler, no redundancy, right level of detail? |

| Score | Band | Meaning |
|-------|------|---------|
| 0-2 | Critical | Broken, missing, or harmful |
| 3-4 | Poor | Exists but significantly incomplete |
| 5-6 | Acceptable | Meets minimum standards |
| 7-8 | Good | Complete, well-sourced, useful |
| 9-10 | Excellent | Authoritative, comprehensive, exemplary |

**Page score** = average of 6 criteria. Target: all pages ≥ 7.

See `references/RUBRIC.md` for detailed scoring guidance per criterion.

</supporting-info>
