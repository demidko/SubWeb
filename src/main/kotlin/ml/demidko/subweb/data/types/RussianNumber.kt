package ml.demidko.subweb.data.types

class RussianNumber(s: String) :
  CharSequence by s
    .trim()
    .lowercase()
    .replace("(", "")
    .replace(")", "")
    .replace("-", "")
    .replace(" ", "")
    .run({
      when (startsWith('8')) {
        true -> replaceFirst("8", "+7")
        else -> this
      }
    })