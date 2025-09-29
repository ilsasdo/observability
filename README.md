```sh
colima start --network-address
```

## What is Observability

1. Logs
2. Metrics
3. Traces

## Vanilla

1. Startup apps without tracing.
2. Show Grafana stack and connect backends.
3. No logs are shown.

## Otel Config

1. Add OTEL collector to `compose.yml`
2. Describe `config/otel-collector/otel-collector-config.yml`
3. Instrument app
4. Describe env variables in `compose.yml`

## Slow Traces

1. show slow traces in logs

## Resources

1. www.opentelemetry.io
2. `grafana/otel-lgtm`: https://grafana.com/blog/2024/03/13/an-opentelemetry-backend-in-a-docker-image-introducing-grafana/otel-lgtm/
