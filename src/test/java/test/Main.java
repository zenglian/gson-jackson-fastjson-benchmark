package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import org.junit.Test;

/**
 * @author Leon Zeng
 * @since 2019/1/30 10:21
 */
public class Main {
  private static final Gson gson = new Gson();
  private static final ObjectMapper jackson = new ObjectMapper();
  private static final double delta = 0.0001;

  @Test
  public void tests() throws IOException {
    System.out.println("Time spent in nanoseconds, the smaller the better:");
    System.out.println();
    System.out.printf("%8s %8s %8s %8s", "Parser", "toJson", "toPojo", "Total");
    System.out.println();
    //compareParsers(1000);
    compareParsers(10000);
    compareParsers(100000);
    //compareParsers(1000000);
  }

  private void compareParsers(int laps) throws IOException {
    System.out.printf("----------%d laps--------------\n", laps);
    testParser(Parser.GSON, laps);
    testParser(Parser.JACKSON, laps);
    testParser(Parser.FASTJSON, laps);
    System.out.println();
  }

  private long testParser(Parser parser, int laps) throws IOException {
    long marshallTime = 0;
    long unmarshallTime = 0;

    for (int i = 0; i < laps; i++) {
      MyPojo myPojo = new MyPojo();

      long t1 = System.nanoTime();
      String json = marshall(parser, myPojo);
      long t2 = System.nanoTime();
      MyPojo myPojo2 = unmarshall(parser, json);
      long t3 = System.nanoTime();
      marshallTime += (t2 - t1);
      unmarshallTime += (t3 - t2);

      assertEquals(myPojo, myPojo2);
    }

    long total = (unmarshallTime + marshallTime) / laps;
    System.out.printf("%8s %8d %8d %8d\n", parser, marshallTime / laps, unmarshallTime / laps, total);

    return total;
  }

  private String marshall(Parser parser, MyPojo pojo) throws IOException {
    return parser == Parser.GSON
        ? gson.toJson(pojo)
        : parser == Parser.JACKSON ? jackson.writeValueAsString(pojo)
        : JSON.toJSONString(pojo);
  }

  private MyPojo unmarshall(Parser parser, String json) throws IOException {
    return parser == Parser.GSON
        ? gson.fromJson(json, MyPojo.class)
        : parser == Parser.JACKSON ? jackson.readValue(json, MyPojo.class)
        : JSON.parseObject(json, MyPojo.class);
  }

  enum Parser {
    GSON,
    JACKSON,
    FASTJSON
  }
}
