package org.letheproject.lethecore.duplication;

import org.mariuszgromada.math.mxparser.*;

public class DuplicationFormulaParser {
    private String formula;
    private long duplication;

    public DuplicationFormulaParser(String formula, long nodes) {
        boolean s0 = License.iConfirmNonCommercialUse("LetheProject");
        boolean s1 = License.checkIfUseTypeConfirmed();
        duplication = parse(formula, nodes);
    }

    public void update(long nodes) {
        duplication = parse(formula, nodes);
    }

    public void update(String formula, long nodes) {
        this.formula = formula;
        duplication = parse(formula, nodes);
    }

    private long parse(String configurationFormula, long nodes) {
        String formula = configurationFormula.replace("x", String.valueOf(nodes));
        Expression expression = new Expression(formula);
        return (long) expression.calculate();
    }

    public long get() {
        return duplication;
    }
}
