---
title: Grant Application Workflow
type: workflow
tldr: "Catalyst Program application strategy"
sources:
  - raw/docs/catalyst-program.md
related:
  - "[[catalyst-program]]"
  - "[[xr-device-types]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Grant Application Workflow

Strategy for applying to the [[catalyst-program]]. Deadline: **June 30, 2026**.

## Application Link

https://docs.google.com/forms/d/e/1FAIpQLSesZlBGg010S0K9Xm5ZvxTt2WMzHGnKtBUYEhBrzd0_uwmHEA/viewform

## What Reviewers Look For

1. **Vertical alignment** — does your app fit a priority vertical?
2. **Developer readiness** — team skills, timeline, existing work
3. **Funding articulation** — clear budget with specific needs
4. **Innovation** — novel XR use case, market potential

## Priority Verticals

- Media & Entertainment
- Gaming
- Productivity & Learning
- Discovery & Navigation
- Messaging & Social
- Health & Wellness
- Commerce & Payments

## Our Strategy: Quest MVP → Grant → Glasses Port

We have a Quest 3S. We don't have wired glasses. The plan:

1. **Before June 30**: Build working live captioning MVP on Quest 3S (passthrough + spatial captions + translation)
2. **June 30**: Submit application with Quest demo as proof of developer readiness
3. **July 15+**: Receive wired glasses dev kit, port caption pipeline to Compose for XR

This is strong because:
- **Working prototype** beats a pitch deck — reviewers see real developer readiness
- The caption pipeline (ASR → translate → display) is device-agnostic — only the display layer changes for glasses
- Demonstrates we can ship on one platform and extend to another

## Preparation Checklist

- [ ] Build Quest 3S MVP: passthrough + ASR + translation + spatial caption panel
- [ ] Record demo video showing real-time captioning in passthrough mode
- [ ] Define app as **Health & Wellness / Accessibility** vertical
- [ ] Articulate funding needs: wired glasses dev kit, accessibility user testing, development time
- [ ] Prepare 6-month timeline: Quest polish (month 1-2) → glasses port (month 3-4) → testing (month 5) → publish (month 6)
- [ ] Emphasize on-device privacy (no audio leaves device) and market size (67M+ Americans with hearing difficulty)

## Application Link

https://docs.google.com/forms/d/e/1FAIpQLSesZlBGg010S0K9Xm5ZvxTt2WMzHGnKtBUYEhBrzd0_uwmHEA/viewform

## Post-Selection Timeline

1. **July 15** — Selection notification
2. Sign program agreements, receive wired glasses dev kit
3. Milestone 1: Technical design for glasses port
4. Milestone 2: Implementation (port caption pipeline to Compose for XR)
5. Milestone 3: Publish on Google Play (both Quest and glasses builds)
