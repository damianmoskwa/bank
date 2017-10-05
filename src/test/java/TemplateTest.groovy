import org.junit.Test
import pl.training.groovy.bank.Template

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertEquals

class TemplateTest {

    private static final String EXPECTED_RESULT = 'My name is Jan Kowalski'

    private Template template = new Template('My name is ${firstName} ${lastName}')
    private Map<String, String> parameters = [firstName: 'Jan', lastName: 'Kowalski']

    @Test
    void shouldEvaluateTextWithExpression() {
        assertEquals(EXPECTED_RESULT, template.evaluate(parameters))
    }

    @Test(expected = IllegalArgumentException.class)
    void shouldThrowExceptionWhenParameterIsMissing() {
        template.evaluate([:])
    }

    @Test
    void shouldIgnoreExtraParameters() {
        parameters['age'] = '12'
        assertEquals(EXPECTED_RESULT, template.evaluate(parameters))
    }
}
