
```sh
colima start --network-address
```

## What is Observability

1. Logs
2. Metrics
3. Traces

## What is OpenTelemetry

1. framework, standard, specification
2. collects data from different sources
   3. baggage, context, traces, metrics, logs
3. exporters: prometheus, jaeger, zipkin, otlp, ...
4. processors: batch, filter, ...
5. receivers: jaeger, otlp, ...
6. pipelines: ...

## Components

1. OTEL Collector
   1. exporters
   2. processors
   3. receivers
   4. pipelines
2. Loki
3. Tempo
4. Prometheus
5. Grafana

## Configuration

1. Autoinstrumentation: https://grafana.com/docs/loki/latest/operations/autodiscovery/
2. TraceID in log: `logging.pattern.level=trace_id=%mdc{trace_id} span_id=%mdc{span_id} trace_flags=%mdc{trace_flags} %5p`
3. Custom Span in Code
4. Disable some autoinstrumentations
5. rabbit, mysql, otelcollector metrics
6. grafana dashboards

## Resources

1. `grafana/otel-lgtm`: https://grafana.com/blog/2024/03/13/an-opentelemetry-backend-in-a-docker-image-introducing-grafana/otel-lgtm/

## Issues

1. beware collecting too many things... on long lasting sessions it will overflow tempo limits.
2. beware of tracing with baggage: it can lead to data leak.