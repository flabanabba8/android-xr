---
title: "Source: Glasses First Activity"
type: source-summary
tldr: "Projected activity architecture"
sources:
  - raw/docs/glasses-first-activity.md
related:
  - "[[compose-glimmer]]"
  - "[[jetpack-projected]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: First Activity for AI Glasses

**Source:** developer.android.com/develop/xr/jetpack-xr-sdk/ai-glasses/first-activity
**Fetched:** 2026-05-21

## Key Takeaways

1. Glasses use **projected activity** — runs on phone, projected to glasses
2. Manifest key: `android:requiredDisplayCategory="xr_projected"`
3. Must check `ProjectedDeviceController.capabilities` at runtime (not all glasses have displays)
4. Use `ProjectedPermissionsResultContract` for hardware permission requests
5. `ProjectedDisplayController` manages display lifecycle
6. Start with `ProjectedContext.createProjectedActivityOptions()`
7. Monitor connection via `ProjectedContext.isProjectedDeviceConnected()` Flow
8. UI: Compose Glimmer with `GlimmerTheme`
