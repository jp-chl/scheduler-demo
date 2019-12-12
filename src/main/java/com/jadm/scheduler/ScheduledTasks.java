package com.jadm.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Clase donde se registran metodos a ser itinerados.
 * Los metodos deben:
 * - Tener la anotacion "@Scheduled"
 * - Retornar void
 * - No tener parametros
 *
 * El nombre del thread (en el log de cada metodo) no es el
 * por defecto. Se configura en la clase #com.jadm.config.SchedulerConfig
 */
@Component
public class ScheduledTasks {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Metodo se ejecuta cada X milisegundos (en este ejemplo: 2000)
     *
     * Se activa descomentando la linea con la anotacion "@Scheduled"
     */
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {

        LOG.info("Current Thread : {}. Fixed Rate Task :: Execution Time - {}",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()));
    } // fin void scheduleTaskWithFixedRate()

    /**
     * Metodo se ejecuta X milisegundos despues de terminada su ejecucion
     * (en este ejemplo: el metodo se demora aprox 5 segundos, por lo que el log se imprime
     * cada 7)
     */
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {

        LOG.info("Current Thread : {}. Fixed Delay Task :: Execution Time - {}",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()));
        try {

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            LOG.error("Ran into an error {}", e);
            //throw new InterruptedException(e.toString());
        }
    } // fin void scheduleTaskWithFixedDelay()

    /**
     *  Metodo se ejecuta cada X milisegundos (en este ejemplo: 2000)
     *  luego de una pausa inicial (initialDelay)
     */
    //@Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {

        LOG.info("Current Thread : {}. Fixed Rate Task with Initial Delay :: Execution Time - {}",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()));
    } // fin void scheduleTaskWithInitialDelay()

    /**
     * Se programa la itineracion del metodo en formato crontab
     */
    //@Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {

        LOG.info("Current Thread : {}. Cron Task :: Execution Time - {}",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()));
    } // fin void scheduleTaskWithCronExpression()
} // fin class ScheduledTasks
