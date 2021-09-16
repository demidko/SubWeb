package ml.demidko.subweb.tags

import ml.demidko.subweb.text.TrimmedLowercase

class Name(parts: Set<TrimmedLowercase>) : Tag, Set<TrimmedLowercase> by parts