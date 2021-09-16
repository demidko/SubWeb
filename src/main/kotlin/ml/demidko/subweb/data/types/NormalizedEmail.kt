package ml.demidko.subweb.data.types

class NormalizedEmail(s: String):
  CharSequence by s
    .trim()
    .lowercase()
    .replace(".", "")