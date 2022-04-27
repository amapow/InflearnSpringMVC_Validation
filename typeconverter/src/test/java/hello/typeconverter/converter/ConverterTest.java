package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void IntegerToString() {
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String result = integerToStringConverter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter ipPortToString = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        String result = ipPortToString.convert(ipPort);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void StringToIpPort() {
        StringToIpPortConverter stringToIpPortConverter = new StringToIpPortConverter();
        String ipPort = "127.0.0.1:8080";
        IpPort result = stringToIpPortConverter.convert(ipPort);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

}