---
name: freshness-check
description: Delta-based wiki freshness scan. Only checks pages modified or stale since last run. Use with /loop for continuous background quality monitoring.
user_invocable: true
---

# Freshness Check (Delta Processing)

Scan wiki pages for staleness using delta processing — only check what changed since last run.

<what-to-do>

1. **Read last check timestamp** from `wiki/hot.md`
2. **Scan all wiki pages** for `last_verified` dates
3. **Skip pages** verified within 90 days
4. **For stale pages:**
   - Check if the raw source has been modified since `last_verified`
   - If yes: flag for re-verification (content may have drifted)
   - If no: bump `last_verified` to today (claims still valid)
5. **Log results** to `wiki/hot.md` using compressed observation format
6. **Report:** pages checked, pages skipped, pages flagged, pages bumped

</what-to-do>

<supporting-info>

## Compressed Observation Format

```
🔄 YYYY-MM-DD Freshness check: N checked, M flagged, K bumped
```

## Delta Rules

- Only process pages not checked in the current cycle
- Never re-check a page verified today
- Flag pages whose raw sources changed since last_verified
- Bump pages whose raw sources haven't changed

</supporting-info>
