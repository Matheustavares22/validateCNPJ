/**
 @author: Matheus Tavares
  *
 */
static void main(String[] args) {
    // write your test of cnpj here
    println("test 1: " + validadeCNPJ("14.696.397/0001-02"))
    println("test 2: " + validadeCNPJ("14.6a96b.397/0001-02"))
    println("test 3: " + validadeCNPJ())
    println("test 4: " + validadeCNPJ(0))
    println("test 5: " + validadeCNPJ(14696397000102))
    println("test 6: " + validadeCNPJ("00.000.000/0000-00"))
    println("test 7: " + validadeCNPJ("0aaa0aaa"))
    println("test 8: " + validadeCNPJ(14696397000102))
    println("test 9: " + validadeCNPJ("05.334.963/0001-45"))
    println("test 10: " + validadeCNPJ("52.610.825/0001-21"))
    println("test 11: " + validadeCNPJ("42.272.84/0001-83"))
    println("test 12: " + validadeCNPJ("14.174.602/0001-70"))
    println("test 13: " + validadeCNPJ("34.286.283/0002-00"))
    println("test 14: " + validadeCNPJ("96.261.765/0001-54"))
    println("test 15 CNPJ BLUECX INFINITY: " + validadeCNPJ("20.093.622/0001-19"))
    println("test 16 CNPJ ITAÚ: " + validadeCNPJ("60.701.190/0001-04"))
    println("test 17: " + validadeCNPJ("60.701.     23"))
    println("test 18: " + validadeCNPJ(3 * 123))
    println("test 19: " + validadeCNPJ(22222222222222))

}

static validadeCNPJ(cnpj) {
    final CNPJ_MAX_LENGTH = 14

    cnpj = removeInvalidCharacters(cnpj)

    if (isValidLength(cnpj, CNPJ_MAX_LENGTH)) {
        return "error: length invalid"
    }

    if (isInvalidEntry(cnpj)) {
        return "error: invalid entry"
    }

    //length without digits
    def length = cnpj?.length() - 2
    //numbers without digits
    def numbers = cnpj?.substring(0, length)
    def digits = cnpj?.substring(length)
    def sum = 0
    def pos = length - 7

    for (int i = length; i >= 1; i--) {
        sum += Integer.parseInt(numbers.charAt(length - i).toString()) * pos--;
        if (pos < 2) {
            pos = 9
        }
    }

    def result = sum % 11 < 2 ? 0 : 11 - sum % 11
    //verifing first digit
    if (isDigitValid(result, digits, 0)) {
        return "error:1 invalid cnpj"
    }

    length = length + 1
    numbers = cnpj?.substring(0, length)
    pos = length - 7;
    sum = 0
    for (int i = length; i >= 1; i--) {
        sum += Integer.parseInt(numbers.charAt(length - i).toString()) * (pos--);
        if (pos < 2) {
            pos = 9
        }
    }

    result = sum % 11 < 2 ? 0 : 11 - sum % 11;
    //verifing second digit
    if (isDigitValid(result, digits, 1)) {
        return "error:2 invalid cnpj"
    }

    return "valid"
}

private static boolean isDigitValid(int result, String digits, Integer chatAtValue) {
    result.toString() != digits.charAt(chatAtValue).toString()
}

private static String removeInvalidCharacters(cnpj) {
    cnpj = cnpj?.toString()
    cnpj = cnpj?.replaceAll("[^0-9]", "")
    cnpj
}

private static boolean isValidLength(String cnpj, int CNPJ_MAX_LENGTH) {
    cnpj?.length() != CNPJ_MAX_LENGTH
}

private static boolean isInvalidEntry(String cnpj) {
    cnpj == "00000000000000" || cnpj == "11111111111111" ||
            cnpj == "22222222222222" || cnpj == "33333333333333" ||
            cnpj == "44444444444444" || cnpj == "55555555555555" ||
            cnpj == "66666666666666" || cnpj == "77777777777777" ||
            cnpj == "88888888888888" || cnpj == "99999999999999"
}


