public class Dec2Bin {

    public static String toBin(String decNumber) {
        int dotIdx = decNumber.indexOf('.');
        try {
            if (dotIdx == -1) {
                return binInt(Integer.parseInt(decNumber));
            } else if (dotIdx == 0) {
                return '.' + binDec(Double.parseDouble(decNumber));
            } else if (dotIdx == decNumber.length() - 1) {
                return binInt(Integer.parseInt(decNumber.substring(0, decNumber.length() - 1)));
            }
            String intPart = decNumber.substring(0, dotIdx);
            String decPart = decNumber.substring(dotIdx);

            return binInt(Integer.parseInt(intPart)) + '.' + binDec(Double.parseDouble(decPart));
        } catch (IllegalArgumentException e) {
            return "ERROR";
        }
    }

    public static String binInt(int intPart) {
        StringBuilder res = new StringBuilder();

        int rem = intPart / 2;
        res.append(intPart % 2);
        while (rem > 0) {
            res.insert(0, rem % 2);
            rem = rem / 2;
        }

        return res.toString();
    }

    public static String binDec(double decPart) {
        StringBuilder res = new StringBuilder();

        res.append('0');
        double rem = decPart * 2;
        while (rem > 0) {
            if (rem >= 1) {
                rem -= 1;
                res.append('1');
            } else {
                res.append('0');
            }

            if (res.length() > 32) {
                throw new IllegalArgumentException(decPart + " cannot be represented as binary decimal");
            }
            rem = rem * 2;
        }

        return res.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 256; i++) {
            assert Integer.toBinaryString(i).equals(binInt(i)) : Integer.toBinaryString(i) + " != " + binInt(i);
        }

        assert toBin("3.72").equals("ERROR") : toBin("3.72");
        assert toBin("3.75").equals("11.011") : toBin("3.75");
        assert toBin("11.1 ").equals("ERROR");
        assert toBin("11").equals("1011");
        assert toBin("11.").equals("1011") : toBin("11.");
        assert toBin(".1").equals("ERROR");
    }

}
