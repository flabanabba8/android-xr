---
name: wiki-query
description: Answer a question about Android XR development by consulting the wiki. Navigates via index, reads relevant pages, synthesizes an answer with citations. Files valuable new findings back as wiki pages (anti-evaporation principle).
user_invocable: true
trigger: "when the user asks a question about Android XR, Jetpack XR SDK, or the Catalyst grant program"
argument-hint: "[question]"
---

# Query the Wiki

Answer a question about Android XR using the knowledge wiki.

## Steps

1. **Read `wiki/hot.md`** for recent context and active investigations
2. **Read `wiki/index.md`** to identify relevant pages
3. **Read the most relevant pages** — use `tldr` fields to scan efficiently, then read full content for the best matches
4. **Synthesize an answer** with `[[wikilink]]` citations to wiki pages
5. **If the wiki doesn't cover the topic**, check `raw/docs/` and `raw/articles/` sources directly
6. **Anti-Evaporation: If the answer is novel and valuable, file it as a new wiki page.** Every conversation should produce both an answer AND a wiki update when the finding is worth preserving.

## Output Format

- Cite sources using `[[page-name]]` wikilinks
- Include code examples where relevant
- Be concise but complete
- Flag any uncertainty with "Note: this may be outdated — last_verified: YYYY-MM-DD"
- Check `last_verified` dates on pages you cite — warn if >90 days old

## If the Wiki is Insufficient

If neither the wiki nor raw sources answer the question:
1. Suggest using WebSearch or WebFetch to check the latest docs at developer.android.com/develop/xr
2. If Playwright MCP is available, offer to browse the docs directly
3. Offer to ingest any new findings with `/ingest`
4. If the user provides the answer, offer to save it as a wiki page
