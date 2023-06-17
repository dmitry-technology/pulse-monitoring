package telran.pulse.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import telran.pulse.monitoring.dto.Sensor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@SpringBootApplication
public class PulseSourceAppl {

    int count = 0;
    Random rnd = new Random();
    static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(PulseSourceAppl.class, args);
    }

    @Bean
    Supplier<Sensor> pulseSupplier() {
        return this::pulseRandomGeneration;
    }

    private Sensor pulseRandomGeneration() {
        int id = getRandomNumber(1, 10);
        int value = getRandomNumber(40, 250);
        Sensor sensor = new Sensor(id, value);
//        if (count > 100) {
//            ctx.close();
//        }
        System.out.println("send sensor "+ id);
        return sensor;
    }

    private int getRandomNumber(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
