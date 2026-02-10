package com.learning.globetrotter.controller;

import com.learning.globetrotter.model.Destination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class TravelController {

    // Mock Database
    private List<Destination> bucketList = Arrays.asList(
            new Destination(1, "Kyoto", "Japan",
                    "Famous for its classical Buddhist temples, as well as gardens, imperial palaces, Shinto shrines and traditional wooden houses."),
            new Destination(2, "Reykjavik", "Iceland",
                    "The coast of Iceland is home to the Blue Lagoon and the Northern Lights."),
            new Destination(3, "Santorini", "Greece", "Recognizable by its whitewashed, cubiform houses."));

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("destinations", bucketList);
        return "dashboard"; // Loads dashboard.html
    }

    @GetMapping("/details")
    public String showDetails(@RequestParam("id") int id, Model model) {
        // Find the destination by ID
        Destination selected = bucketList.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("place", selected);
        return "details"; // Loads details.html
    }
}
