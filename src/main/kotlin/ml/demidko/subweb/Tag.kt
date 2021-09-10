package ml.demidko.subweb

import java.io.Serializable

/**
 * Тег для описания сущностей. Атомарная единица нашей системы.
 * Просто кусок текста который мы наделяем смыслом, типизируем.
 */
interface Tag : Serializable {

  /**
   * Собственно текстовые данные тега
   */
  val text: String

  /**
   * Telegram ID того кто добавил тег. Пригодится, чтобы связаться и выяснить подробности в случае чего
   */
  val whoAdded: Long
}

class Note(override val text: String, override val whoAdded: Long) : Tag

class OrganizationName(override val text: String, override val whoAdded: Long) : Tag

fun normalizeOrganizationName(text: String, whoAdded: Long) = OrganizationName(text.trim().lowercase(), whoAdded)

/**
 * Адрес, метка, координаты, дом, город
 */
class Geolocation(override val text: String, override val whoAdded: Long) : Tag

/**
 * Должность, пост
 */
class Position(override val text: String, override val whoAdded: Long) : Tag

/**
 * Тег предназначен для однозначной идентификации одной конкретной сущности.
 * ИНН, паспорт, номер телефона, почта, идентификатор ресурса.
 * Эта абстракция нужна для склейки сущностей-дубликатов в одну.
 * Для склейки, должно быть полное вхождение всех тегов вклеиваемой сущности, во избежание ложных срабатываний.
 */
interface Identifier : Tag

class FullName(override val text: String, override val whoAdded: Long) : Identifier

fun normalizeFullName(text: String, whoAdded: Long) = FullName(text.trim().lowercase(), whoAdded)

class PhoneNumber(override val text: String, override val whoAdded: Long) : Identifier

fun normalizePhoneNumber(text: String, whoAdded: Long): PhoneNumber {
  val n =
    text.trim()
      .replace("-", "")
      .replace(" ", "")
      .replace("(", "")
      .replace(")", "")
  return PhoneNumber(
    if (n.startsWith("8")) n.replaceFirst("8", "+7") else n,
    whoAdded
  )
}

/**
 * Данные паспорта, ИНН и т п
 */
class DocumentNumber(override val text: String, override val whoAdded: Long) : Identifier

fun normalizeDocumentNumber(text: String, whoAdded: Long) =
  DocumentNumber(text.trim().lowercase(), whoAdded)

class Email(override val text: String, override val whoAdded: Long) : Identifier

fun normalizeEmail(text: String, whoAdded: Long) = Email(text.trim().lowercase(), whoAdded)

/**
 * Сетевой уникальный адрес
 */
class Uri(override val text: String, override val whoAdded: Long) : Identifier

fun normalizeUri(text: String, whoAdded: Long) =
  Uri(text.trim(' ', '/').lowercase(), whoAdded)