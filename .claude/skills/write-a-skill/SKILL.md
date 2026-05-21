---
name: write-a-skill
description: Create new agent skills with proper structure, progressive disclosure, and bundled resources. Use when user wants to create, write, or build a new skill.
user_invocable: true
---

# Writing Skills

<what-to-do>

1. **Gather requirements** — what task, what use cases, scripts needed?, reference materials?
2. **Read SKILL-TEMPLATE.md** for the standard format
3. **Draft the skill** — SKILL.md (<100 lines), references/ if needed, scripts/ if deterministic
4. **Review with user** — iterate until approved

</what-to-do>

<supporting-info>

## Skill Structure

```
skill-name/
├── SKILL.md           # Main instructions (<100 lines, required)
├── references/        # Split deep content here
└── scripts/           # Utility scripts (deterministic ops only)
```

## Description Rules

The description is the **only thing the agent sees** when picking skills. Max 1024 chars, third person. First sentence: what it does. Second: "Use when [triggers]."

## When to Split

- SKILL.md exceeds 100 lines → move detail to references/
- Deterministic operations → utility scripts (saves tokens vs generated code)

## Review Checklist

- [ ] Description includes "Use when..." triggers
- [ ] SKILL.md under 100 lines
- [ ] `user_invocable: true` in frontmatter
- [ ] Uses `<what-to-do>` / `<supporting-info>` XML tags
- [ ] Concrete examples included

</supporting-info>
