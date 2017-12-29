# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class TagItem(scrapy.Item):
    name = scrapy.Field()
    href = scrapy.Field()


class BookHrefItem(scrapy.Item):
    title = scrapy.Field()
    href = scrapy.Field()


class BookInfoItem(scrapy.Item):
    isbn = scrapy.Field()
    title = scrapy.Field()
    publisher = scrapy.Field()
    pubdate = scrapy.Field()
    author = scrapy.Field()
    cover_url = scrapy.Field()
    category = scrapy.Field()