package com.algo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class App {

    @Data
    @Builder
    public static class DemoModel {
        private String name;
        private LocalDateTime createdAt;
        private Optional<String> description;
        private ImmutableList<String> tags;
    }

    public static void main(String[] args) {
        log.info("Starting Algo LeetCode App...");

        // Demonstrate Apache Commons Lang
        String appTitle = "algo-leetcode project";
        log.info("Capitalized title: {}", StringUtils.capitalize(appTitle));

        // Demonstrate Guava
        ImmutableList<String> tags = ImmutableList.of("java", "maven", "leetcode");
        log.info("Guava ImmutableList: {}", tags);

        // Demonstrate Lombok and Builders
        DemoModel model = DemoModel.builder()
                .name("Sample Run")
                .createdAt(LocalDateTime.now())
                .description(Optional.of("Demoing standard stack setup"))
                .tags(tags)
                .build();
        log.info("Lombok Model: {}", model);

        // Demonstrate Jackson (FasterXML) setup & serialization
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(new GuavaModule());

        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
            log.info("Serialized JSON:\n{}", json);

            DemoModel deserialized = mapper.readValue(json, DemoModel.class);
            log.info("Deserialized Model successfully! Name: {}", deserialized.getName());
        } catch (Exception e) {
            log.error("JSON Serialization/Deserialization failed", e);
        }
    }
}
