# -*- coding: utf-8 -*-
import scrapy

from libdata import items


class TagspiderSpider(scrapy.Spider):
    name = 'TagSpider'
    allowed_domains = ['libdata']
    start_urls = ['https://book.douban.com/tag/?view=type']

    def parse(self, response):
        for tag in response.xpath("//div[@class='article']//td/a"):
            tag_item = items.TagItem()
            tag_item['name'] = tag.xpath('text()').extract_first()
            tag_item['href'] = response.urljoin(tag.xpath('@href').extract_first())
            yield tag_item
