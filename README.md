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

# Presentazione

1. cosa faremo oggi
    1. breve panoramica observability
    2. configurazione di otel/grafana e demo
2. cosa è observability?
3. logs
4. traces
5. metrics
6. una moltitudine di librerie, tool, framework
7. OpenTelemetry + Grafana
8. Cos'è opentelemetry?
9. Cosa offre otel:
    1. otel-collector
    2. otel-instrumentation
10. Diagramma dei vari componenti app -> instrumented -> otel-collector -> backend -> grafana
11. Demo con semplice App senza Logs.
    1. instrumenta app con OTEL
    2. mostra configurazione grafana/otel-collector
    3. mostra log su grafana
12. Demo con app con richiesta lenta
    1. configura i trace, mostra i trace su tempo
13. Demo con app che crasha? ######## TODO
    1. configura prometheus per metriche
14. Mostra custom-span nel codice
15. Mostra custom-metrics nel codice
16. Disable some autoinstrumentation
17. Problemi:
    1. beware collecting too many things... on long lasting sessions it will overflow tempo limits.
    2. beware of tracing with baggage: it can lead to data leak.


1. show all the stack
2. start it
3. no logs shows
4. configure otel-collector
5. configure autoinstrumentation
6. show logs/telemetry
7. show slow traces
8. show exception events
9. show how to add custom span -> CloneFactoryService
10. show how to add custom metrics -> CloneFactoryService