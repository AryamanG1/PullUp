# Event Contracts

This directory defines the asynchronous event contracts used across services.

## Rules
- Contracts are immutable once published
- Changes require a new version (v2, v3…)
- Fields may be added but never removed or renamed
- Services own and version their own events
- No shared code is allowed

## Ownership
- driver/* → Driver Service
- ride/* → Rider Service
- assignment/* → Matching Service

## Runtime
- These files are NOT served at runtime
- Kafka is the transport
- Consumers must be backward compatible
