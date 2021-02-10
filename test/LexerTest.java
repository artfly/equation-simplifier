import com.github.artfly.lexer.Lexer;
import com.github.artfly.lexer.Token;
import com.github.artfly.lexer.TokenType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class LexerTest {

    @Test
    public void testIdentifier() {
        List<Token> tokens = new Lexer(new BufferedReader(new StringReader("foo"))).scan();
        Assert.assertThat(getTypes(tokens), CoreMatchers.is(List.of(
                TokenType.IDENTIFIER, TokenType.EOL, TokenType.EOF)));
    }

    private static List<TokenType> getTypes(List<Token> tokens) {
        return tokens.stream().map(Token::tokenType).collect(Collectors.toList());
    }

}
