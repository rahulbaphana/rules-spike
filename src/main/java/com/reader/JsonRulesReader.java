package com.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.mvel.MVELRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonRulesReader {
    private final List<Rule> rules = new ArrayList<>();

    public JsonRulesReader(String jsonRules) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonRules);
        for (JsonNode node : jsonNode) {
            MVELRule thisRule = new MVELRule().name(node.get("name").asText())
                    .description(node.get("description").asText())
                    .priority(node.get("priority").asInt())
                    .when(node.get("condition").asText())
                    .then(node.get("action").toString());
            rules.add(thisRule);
        }
    }

    public List<Rule> allRules() {
        return rules;
    }

    public Optional<Rule> getRuleBy(String nameOfTheRule) {
        return rules.stream().filter(rule -> rule.getName().equals(nameOfTheRule)).findFirst();
    }
}
