---
title: "Using CloakBrowser for Web Automation"
tldr: "Stealth browser for bot-protected sites"
type: how-to
sources: []
related: []
created: 2026-05-26
updated: 2026-05-26
confidence: high
last_verified: 2026-05-26
aliases: [cloakbrowser, cloak-browser]
---

# Using CloakBrowser for Web Automation

CloakBrowser is a stealth Chromium with C++-level fingerprint patches — drop-in Playwright replacement that passes bot detection. Use for scraping protected docs, testing web UIs, or researching APIs behind Cloudflare.

## Quick Start

```python
from cloakbrowser import launch
browser = launch(headless=True)
page = browser.new_page()
page.goto("https://example.com")
text = page.inner_text("body")
browser.close()
```

## One-Liner Page Read

```bash
python3 ~/.claude/skills/cloakbrowser/scripts/read_page.py "https://url"
```

## Key Options

| Option | Purpose |
|---|---|
| `headless=False` | Headed mode — some sites detect headless |
| `humanize=True` | Human-like mouse/keyboard/scroll |
| `proxy="http://..."` | Residential proxy for aggressive sites |
| `geoip=True` | Match timezone/locale to proxy IP |

## Tips

- Use `time.sleep()` instead of `page.wait_for_timeout()`
- Use `page.type()` with delay instead of `page.fill()`
- Returns standard Playwright `Browser` — full API available
