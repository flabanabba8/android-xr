---
title: Jetpack Compose Glimmer
type: concept
tldr: "UI toolkit for display glasses"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[xr-device-types]]"
  - "[[jetpack-projected]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [glimmer, compose-glimmer]
---

# Jetpack Compose Glimmer

Glasses-specific UI toolkit optimized for optical see-through displays. Only for display glasses.

## Design Constraints

Display glasses have fundamentally different constraints than headsets:
- Small, transparent display overlaid on the real world
- Limited color palette (bright colors wash out)
- Clear focus states needed (outlines, not ripples)
- Voice, tap, and swipe input (no controllers)

## Components

Text, Icon, Button, TitleChip, Lists, Cards, Surfaces — all optimized for glasses form factor.

## Theming

- Simplified color palettes for optical see-through
- Typography sized for near-eye reading
- High-contrast focus indicators
- Indirect pointer input support

## Input Methods

- **Automatic Speech Recognition (ASR)** — primary input
- **Tap/swipe** — on glasses temple
- **Voice commands** — via Gemini Live API integration
