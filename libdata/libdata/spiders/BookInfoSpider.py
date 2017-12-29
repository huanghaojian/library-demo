# -*- coding: utf-8 -*-
import csv
import json
import logging
import re

import scrapy
from libdata import items


class BookinfospiderSpider(scrapy.Spider):
    name = 'BookInfoSpider'
    allowed_domains = ['libdata']
    api_prefix = 'https://api.douban.com/v2/book/'
    api_fields = '?fields=title,publisher,pubdate,author,category,image,catalog,isbn13'
    start_urls = []

    def __init__(self):
        with open("../data/book_href.csv", "r") as csvfile:
            reader = csv.DictReader(csvfile, delimiter=',',
                                    quotechar='|', quoting=csv.QUOTE_MINIMAL)
            pattern = re.compile(r'\d+')
            for line in reader:
                book_id = pattern.findall(line['href'])[0]
                if book_id is not None:
                    full_url = self.api_prefix + book_id + self.api_fields
                    self.start_urls.append(full_url)
                else:
                    continue

    def parse(self, response):
        info = json.loads(response.body.decode("utf-8"))
        logging.info("json:", info)
        book_info_item = items.BookInfoItem()
        book_info_item['isbn'] = info['isbn13']
        book_info_item['title'] = info['title']
        book_info_item['publisher'] = info['publisher']
        book_info_item['pubdate'] = info['pubdate']
        book_info_item['author'] = info['author'][0]
        book_info_item['cover_url'] = info['image']
        book_info_item['category'] = info['catalog']
        return book_info_item
