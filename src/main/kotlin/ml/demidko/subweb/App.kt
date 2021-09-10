import ezvcard.Ezvcard
import ezvcard.VCard
import java.io.File

fun main() {
  val lst = Ezvcard.parse(File("bookmarks/contacts-2019.vcf"))

  println(lst.all().size)
  lst.all().forEach {
    println(it.formattedName?.value ?: it.structuredName)
  }

  /**
   * Функции бота:
   * 1. дамп сущностей
   * 2. поиск сущностей
   * 3. отредактировать сущность: добавить тег (по типу, предложить выбрать тип)
   * 4. загрузить сущность: добавить тег (по типу, предложить выбрать тип, из базовых идентификаторов)
   *    можно сразу просить пользователя обогатить сущность еще какими-нибудь характеристиками.
   * 5. загрузить все сущности из google vcf / firefox json
   * 6. Автоматическая агрегация сущностей в фоне на основе полного вхождения идентификаторов.
   */
}