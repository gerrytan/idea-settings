workspace(name = "idea_settings")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:java.bzl", "java_import_external")

# To view newer releases: https://www.jetbrains.com/intellij-repository/releases/
IDEA_VERSION = "2020.2.1"

IDEA_IC_SHA256 = "a2777ea279b7bd725d167e94b28a488ea4dbd22a363255c82b9a1b1a208d4c3e"

IDEA_IU_SHA256 = "a5543b6e3fd702846a916621c039f7c808664d67080b8095c1fcc3a96571166a"

IDEA_IC_SOURCES_SHA256 = "c66da51909ed3e8e649f085bd7cfdf18f06e2d9b736db3e54ef730a31a7ab8a3"

http_archive(
    name = "idea-IC",
    build_file = "idea-IC.BUILD",
    sha256 = IDEA_IC_SHA256,
    url = "https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIC/{0}/ideaIC-{0}.zip".format(IDEA_VERSION),
)

java_import_external(
    name = "idea-IC-sources",
    jar_sha256 = IDEA_IC_SOURCES_SHA256,
    jar_urls = ["https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIC/{0}/ideaIC-{0}-sources.jar".format(IDEA_VERSION)],
)

http_archive(
    name = "idea-IU",
    build_file = "idea-IU.BUILD",
    sha256 = IDEA_IU_SHA256,
    url = "https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIU/{0}/ideaIU-{0}.zip".format(IDEA_VERSION),
)

http_archive(
    name = "bazel-deps",
    sha256 = "5dac398f0dc57f76566642cf21f10960eed83f4bb56c5860170c0582f5581194",
    strip_prefix = "bazel-deps-e3f77e22d9f5b070915067a766607cfc96835c98",
    url = "https://github.com/johnynek/bazel-deps/archive/e3f77e22d9f5b070915067a766607cfc96835c98.zip",
)

http_archive(
    name = "CheckStyle-IDEA",
    build_file = "CheckStyle-IDEA.BUILD",
    sha256 = "4e7219478ff923736a824bee1e262b6090116ae20971d35dc0b1cd464090d293",
    strip_prefix = "CheckStyle-IDEA",
    url = "https://github.com/jshiell/checkstyle-idea/releases/download/5.40.0/CheckStyle-IDEA-5.40.0.zip",
)

http_archive(
    name = "File-Watchers",
    build_file = "File-Watchers.BUILD",
    sha256 = "c420c33f9118e4fbb8769b44c0c8133dfc931571ff9e344d7d94785807e3aeb9",
    strip_prefix = "fileWatcher",
    url = "https://plugins.jetbrains.com/files/7177/92064/fileWatcher-202.6397.21.zip",
)

java_import_external(
    name = "Save-Actions",
    jar_sha256 = "443cf9973ff0ac9e4d517632360f581cbe55d4d906f729ff2b81d69d966d8e25",
    jar_urls = ["https://github.com/dubreuia/intellij-plugin-save-actions/releases/download/v2.0.0/intellij-plugin-save-actions-2.0.0.jar"],
    neverlink = True,
)

# TODO: Upgrade this when a new version comes out; this version is broken in 2020.1.
http_archive(
    name = "SonarLint",
    build_file = "SonarLint.BUILD",
    sha256 = "5950aa7563265678c4086d23f5c0efad73badb8ce574021f53199d5777b2d5be",
    strip_prefix = "sonarlint-intellij",
    url = "https://plugins.jetbrains.com/files/7973/91941/sonarlint-intellij-4.10.0.19739.zip",
)

http_archive(
    name = "intellij_with_bazel",
    sha256 = "bc3187cc94852f2d846cc427b9c1c420aa3f8726886dfe223f98872a0f490e9b",
    strip_prefix = "intellij-485a11132a7fd1b563357fa4263dd6ca3e3dd275",
    url = "https://github.com/bazelbuild/intellij/archive/485a11132a7fd1b563357fa4263dd6ca3e3dd275.zip",
)

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = "4fd769fb0db5d3c6240df8a9500515775101964eebdf85a3f9f0511130885fde",
    strip_prefix = "rules_kotlin-legacy-1.3.0",
    url = "https://github.com/bazelbuild/rules_kotlin/archive/legacy-1.3.0.zip",
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

kotlin_repositories()

kt_register_toolchains()

http_archive(
    name = "rules_pkg",
    sha256 = "02de387c5ef874379e784ac968bf6efffe5285a168cab5a3169e08cfc634fd22",
    url = "https://github.com/bazelbuild/rules_pkg/releases/download/0.2.2/rules_pkg-0.2.2.tar.gz",
)

load("@rules_pkg//:deps.bzl", "rules_pkg_dependencies")

rules_pkg_dependencies()

# Test dependencies
http_archive(
    name = "javapoet",
    build_file_content = """
package(default_visibility = ["//visibility:public"])
filegroup(
    name = "javapoet",
    srcs = glob(["**"]),
)""",
    sha256 = "6b3fc0ae4e321286d09ce633037152a3194f7ebbd6d8b06a9f195fc7c9255d65",
    strip_prefix = "javapoet-javapoet-1.11.1",
    url = "https://github.com/square/javapoet/archive/javapoet-1.11.1.zip",
)

http_archive(
    name = "auto",
    build_file_content = """
package(default_visibility = ["//visibility:public"])
filegroup(
    name = "auto",
    srcs = glob(["**"]),
)""",
    sha256 = "c926daf62d24bb6ad6289e997d67dcacbb1054e6cc2d9ba4c406f509e7c20875",
    strip_prefix = "auto-auto-value-1.6.6",
    url = "https://github.com/google/auto/archive/auto-value-1.6.6.zip",
)

http_archive(
    name = "maven-bin",
    build_file_content = """
package(default_visibility = ["//visibility:public"])
filegroup(
    name = "maven-bin",
    srcs = glob(["**"]),
)""",
    sha256 = "26ad91d751b3a9a53087aefa743f4e16a17741d3915b219cf74112bf87a438c5",
    strip_prefix = "apache-maven-3.6.3",
    url = "https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz",
)

http_archive(
    name = "build_bazel_integration_testing",
    sha256 = "e055ff971787a27d6942a83ffd182953988c88dfa82e89138ccc83bf410a65d6",
    strip_prefix = "bazel-integration-testing-2a4f6c244312c036e0f3a125ee6086637ee7723b",
    url = "https://github.com/bazelbuild/bazel-integration-testing/archive/2a4f6c244312c036e0f3a125ee6086637ee7723b.zip",
)

load("@build_bazel_integration_testing//tools:bazel_java_integration_test.bzl", "bazel_java_integration_test_deps")

bazel_java_integration_test_deps()

http_archive(
    name = "google_bazel_common",
    sha256 = "090d1f394c2bbeae37f091a9d7853bafc7a9b3174d1e100d762fdd07767a2269",
    strip_prefix = "bazel-common-1c8dcb31eed0713306cb6dc07f8334d84c925a01",
    urls = ["https://github.com/google/bazel-common/archive/1c8dcb31eed0713306cb6dc07f8334d84c925a01.zip"],
)

load("@google_bazel_common//:workspace_defs.bzl", "google_common_workspace_rules")

google_common_workspace_rules()

http_archive(
    name = "rules_jvm_external",
    sha256 = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a",
    strip_prefix = "rules_jvm_external-3.0",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/3.0.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

maven_install(
    artifacts = [
        "com.fasterxml.jackson.core:jackson-annotations:2.10.3",
        "com.fasterxml.jackson.core:jackson-core:2.10.3",
        "com.fasterxml.jackson.core:jackson-databind:2.10.3",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.10.3",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.10.3",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.10.3",
        "com.github.victools:jsonschema-generator:4.7.0",
        "com.github.victools:jsonschema-module-jackson:4.7.0",
        "com.google.dagger:dagger:2.26",
        "com.google.guava:guava:27.0.1-jre",
        "javax.inject:javax.inject:1",
        "junit:junit:4.12",
        "org.assertj:assertj-core:3.11.1",
        "org.xmlunit:xmlunit-assertj:2.6.2",
        maven.artifact(
            group = "com.fasterxml.jackson.module",
            artifact = "jackson-module-kotlin",
            version = "2.10.1",
            exclusions = [
                # Keeping this generates a warning about bundling in a Kotlin runtime, but is necessary for deserialisation.
                # "org.jetbrains.kotlin:kotlin-reflect",
                "org.jetbrains.kotlin:kotlin-stdlib",
            ],
        ),
        maven.artifact(
            group = "com.google.dagger",
            artifact = "dagger-compiler",
            version = "2.26",
            exclusions = ["org.jetbrains.kotlin:kotlin-stdlib"],
        ),
        maven.artifact(
            group = "io.mockk",
            artifact = "mockk",
            version = "1.9",
            exclusions = [
                "org.jetbrains.kotlin:kotlin-reflect",
                "org.jetbrains.kotlin:kotlin-stdlib",
            ],
        ),
    ],
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()
