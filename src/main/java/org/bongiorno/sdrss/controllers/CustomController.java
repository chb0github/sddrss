package org.bongiorno.sdrss.controllers;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.OrderedHealthAggregator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

  private final Map<String,Object> customRepo = new HashMap<>();

  private final Map<String,HealthIndicator> indicators;

  @Autowired
  public CustomController(Map<String, HealthIndicator> indicators) {
    this.indicators = indicators;
  }

  @GetMapping("/custom/{id}")
  public Object getCustom(@PathVariable String id) {
    return customRepo.get(id);
  }

  @PostMapping("/custom")
  public Object acceptCustom(@RequestBody CustomPayload body) {
    String tmpId = UUID.randomUUID().toString();
    // not being entirely equal to kotlin - getter in kotlin are generated
    Object result = new Object() {
      public String id = tmpId;
      public String name = body.name;
      public Integer age = body.age;
    };
    customRepo.put(tmpId,result);
    return result;
  }

  @GetMapping("/health")
  public Health getHealth(@RequestParam("deep") Boolean deep){
    if(deep != null && deep)
      return new CompositeHealthIndicator(new OrderedHealthAggregator(),indicators).health();

    return Health.up().build();
  }

  @EqualsAndHashCode
  @ToString
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CustomPayload {
    private String name;
    private Integer age;
  }
}
