package pl.training.groovy.bank

class Template {
    private static final def EXPRESSION_START = /\$\{/
    private static final def EXPRESSION_END = /}/
    private static final def EXPRESSION = /.*\$\{[^}]+}.*/

    private String textWithExpressions

    Template(String textWithExpressions) {
        this.textWithExpressions = textWithExpressions
    }

    String evaluate(Map<String, String> parameters) {
        String result = substituteParameters(parameters)
        validate(result)
        return result
    }

    private String substituteParameters(Map<String, String> parameters) {
        String result = textWithExpressions
        for (parameter in parameters) {
            result = result.replaceAll(createExpression(parameter.key), parameter.value)
        }
        return result
    }

    private void validate(String result) {
        if (result =~ EXPRESSION) {
            throw new IllegalArgumentException()
        }
    }

    private String createExpression(String name) {
        EXPRESSION_START + name + EXPRESSION_END
    }

}
