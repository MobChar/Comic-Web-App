package ComicWebApplication.application.messageBroker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
//  private static final String EXCHANGE_NAME = "sql-server-broadcast";
//  private static final String QUEUE_NAME = "server-new-image";
  
  @Bean
  public MessageConverter jsonMessageConverter() {
      return new Jackson2JsonMessageConverter();
  }
  
  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setUri("amqp://htrlpkhz:vmQjT9RfCPnVmnrRcjzG1ULysTMw6nQj@mustang.rmq.cloudamqp.com/htrlpkhz");
    return connectionFactory;
  }
//  @Bean
//  public Queue createQueue() {
//    //For learning purpose - durable=false,
//    // in a real project you may need to set this as true.
//    return new Queue(QUEUE_NAME, true);
//  }
//  @Bean
//  public Exchange fanoutExchange() {
//    // durable=true, autoDelete=false
//    return new FanoutExchange(EXCHANGE_NAME, true, false);
//  }
//  @Bean
//  public Binding queueBinding() {
//    return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "", null);
//  }
 }