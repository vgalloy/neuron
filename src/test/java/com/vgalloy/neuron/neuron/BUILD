package(default_visibility = ["//visibility:public"])

java_library(
    name = "helper",
    srcs = ["NeuronTestHelper.java"],
    deps = [
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
        "@junit_junit//jar",
    ],
)

java_test(
    name = "boolean_neuron",
    srcs = ["BooleanNeuronTest.java"],
    test_class = "com.vgalloy.neuron.neuron.BooleanNeuronTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
)

java_test(
    name = "simple_neuron",
    srcs = ["SimpleNeuronTest.java"],
    test_class = "com.vgalloy.neuron.neuron.SimpleNeuronTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
)

java_test(
    name = "tanh_neuron",
    srcs = ["TanHNeuronTest.java"],
    test_class = "com.vgalloy.neuron.neuron.TanHNeuronTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
)
