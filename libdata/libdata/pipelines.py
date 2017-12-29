# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html
import csv
import logging

from libdata import get_conn


class BookHrefPipeline(object):
    def __init__(self):
        self.file_name = '../data/book_href.csv'
        self.had_head = 0

    def process_item(self, item, spider):
        if spider.name == 'BookHrefSpider':
            if self.had_head == 0:
                self.had_head = 1
                with open(self.file_name, 'w') as outfile:
                    csv_file = csv.writer(outfile, delimiter=',',
                                          quotechar='|', quoting=csv.QUOTE_MINIMAL)
                    csv_file.writerow(['title', 'href'])
            with open(self.file_name, 'a') as outfile:
                csv_file = csv.writer(outfile, delimiter=',',
                                      quotechar='|', quoting=csv.QUOTE_MINIMAL)
                csv_file.writerow([item['title'], item['href']])
        return item


class BookInfoPipeline(object):
    def __init__(self):
        self.conn = get_conn()

    def process_item(self, item, spider):
        if spider.name == 'BookInfoSpider':
            logging.info(item)
            item = dict(item)
            cursor = self.conn.cursor()
            sql = """INSERT INTO book (isbn, title, publisher, pubdate, author, cover_url,category)
                  VALUES (%s,%s,%s,%s,%s,%s,%s)"""
            params = [
                item['isbn'],
                item['title'],
                item['publisher'],
                item['pubdate'],
                item['author'],
                item['cover_url'],
                item['category']
            ]
            cursor.execute(sql, params)
            self.conn.commit()
            cursor.close()
        return item

    def __del__(self):
        self.conn.close()
