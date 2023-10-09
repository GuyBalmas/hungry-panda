SOURCE_IMAGE = os.getenv("SOURCE_IMAGE", 'harbor.vrabbi.cloud/guy-lab/hungry-panda-source')
LOCAL_PATH = os.getenv("LOCAL_PATH", default='.')
NAMESPACE = os.getenv("NAMESPACE", default='default')

k8s_custom_deploy(
    'hungry-panda',
    apply_cmd="tanzu apps workload apply -f config/workload.yaml --update-strategy replace --debug --live-update" +
               " --local-path " + LOCAL_PATH +
               " --source-image " + SOURCE_IMAGE +
               " --namespace " + NAMESPACE +
               " --yes --output yaml",
    delete_cmd="tanzu apps workload delete -f config/workload.yaml --namespace " + NAMESPACE + " --yes",
    container_selector='workload',

    deps=['pom.xml', './target/classes'],
    live_update=[
      sync('./target/classes', '/workspace/BOOT-INF/classes')
    ]
)

allow_k8s_contexts('guy-tap-demo-admin@guy-tap-demo')

k8s_resource('hungry-panda', port_forwards=["8080:8080"],
            extra_pod_selectors=[{'carto.run/workload-name': 'hungry-panda', 'app.kubernetes.io/component': 'run'}])

update_settings(k8s_upsert_timeout_secs = 60)