import java.util.*;

abstract class Formula {}

class AtomicFormula extends Formula {
    Character c;

    public AtomicFormula(Character c) {
        this.c = c;
    }

    public String toString() {
        return c.toString();
    }
}

class NegationFormula extends Formula {
    Formula formula;

    public NegationFormula(Formula formula) {
        this.formula = formula;
    }

    public String toString() {
        return "-" + formula;
    }
}

class ConjunctionFormula extends Formula {
    Formula formula1, formula2;

    public ConjunctionFormula(Formula formula1, Formula formula2) {
        this.formula1 = formula1;
        this.formula2 = formula2;
    }

    public String toString() {
        return "&" + formula1 + formula2;
    }
}

public class SequentProver {
    public static Formula parse(Deque<Character> input) throws Exception {
        if (input.size() == 0) {
            throw new Exception("parse error");
        }

        Character token = input.pop();

        if (Character.isLowerCase(token)) {
            return new AtomicFormula(token);
        } else if (token == '-') {
            return new NegationFormula(parse(input));
        } else if (token == '&') {
            return new ConjunctionFormula(parse(input), parse(input));
        } else {
            throw new Exception("parse error");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        Deque<Character> input = new ArrayDeque<Character>();

        while (in.hasNextLine()) {
            String line = in.nextLine();

            line = line.replaceAll("[\\\\n\\s]+", "");

            for (int i=0; i<line.length(); i++) {
                input.add(line.charAt(i));
            }
        }

        Formula f = parse(input);

        if (input.size() > 0) {
            throw new Exception("parse error");
        }

        System.out.println(f);

        // Run theorem prover on sequent made from formula `f` here.
    }
}
