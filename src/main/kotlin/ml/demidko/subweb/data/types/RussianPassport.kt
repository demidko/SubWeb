package ml.demidko.subweb.data.types

class RussianPassport(s: String) :
  Set<String> by s
    .split(' ', '-')
    .filterNot(String::isBlank)
    .map(String::lowercase)
    .toSet()