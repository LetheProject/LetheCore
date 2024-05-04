package org.letheproject.lethecore.duplication;

import org.mariuszgromada.math.mxparser.*;

/**
 * Provides functionality for parsing user-defined duplication formulae.
 */
public class DuplicationFormulaParser {
    private String formula;
    private long duplication;

    /**
     * Instantiate a DuplicationFormulaParser.
     * @param formula the formula to parse, in which the x should represent the number of nodes.
     * @param nodes the number of nodes to place in for x in the formula.
     */
    public DuplicationFormulaParser(String formula, long nodes) {
        License.iConfirmNonCommercialUse("LetheProject");
        License.checkIfUseTypeConfirmed();
        duplication = parse(formula, nodes);
    }

    /**
     * Update the current duplication value with a new number of nodes.
     * @param nodes the number of nodes.
     */
    public void update(long nodes) {
        duplication = parse(formula, nodes);
    }

    /**
     * Update the current duplication value with a new formula and number of nodes.
     * @param formula the new formula.
     * @param nodes the new number of nodes.
     */
    public void update(String formula, long nodes) {
        this.formula = formula;
        duplication = parse(formula, nodes);
    }

    private long parse(String configurationFormula, long nodes) {
        String formula = configurationFormula.replace("x", String.valueOf(nodes));
        Expression expression = new Expression(formula);
        return (long) expression.calculate();
    }

    /**
     * Get the duplication amount.
     * @return the duplication amount.
     */
    public long get() {
        return duplication;
    }
}
