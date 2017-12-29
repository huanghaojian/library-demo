# -*- coding: utf-8 -*-
import csv

import scrapy

from libdata import items


class BookhrefspiderSpider(scrapy.Spider):
    name = 'BookHrefSpider'
    allowed_domains = ['libdata']
    start_urls = []

    def __init__(self):
        with open("../data/tag.csv", "r") as csvfile:
            reader = csv.DictReader(csvfile, delimiter=',',
                                    quotechar='|', quoting=csv.QUOTE_MINIMAL)
            for line in reader:
                self.start_urls.append(line['href'])

    def parse(self, response):
        for a in response.xpath("//ul[@class='subject-list']//h2/a"):
            book_href_item = items.BookHrefItem()
            book_href_item['title'] = a.xpath('text()').extract_first().strip().replace('\n', '')
            book_href_item['href'] = a.xpath('@href').extract_first()
            yield book_href_item
