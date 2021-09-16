package ml.demidko.subweb.tags

import ml.demidko.subweb.text.types.RussianNumber

class Phone(number: RussianNumber) : Tag, CharSequence by number