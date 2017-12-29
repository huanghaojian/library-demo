# -*- coding: utf-8 -*-

import os
from scrapy import cmdline

# cmdline.execute命令只能执行一次,execute方法中的最后一句代码是sys.exit(cmd.exitcode),所以当程序只能执行一个execute语句。
# cmdline.execute("scrapy crawl TagSpider -o ../data/tag.csv".split())
# cmdline.execute("scrapy crawl TagSpider".split())
# cmdline.execute("scrapy crawl BookHrefSpider".split())
# cmdline.execute("scrapy crawl BookInfoSpider".split())

# 执行多个spider
# 爬豆瓣书籍分类,直接将保存成csv文件
# os.system("scrapy crawl TagSpider -o ../data/tag.csv")
# 爬各分类中推荐的书籍链接用csv模块在pipelines处写入csv文件
# os.system("scrapy crawl BookHrefSpider")
# 提取书籍ID,访问豆瓣API接口的图书信息存入数据库,豆瓣接口一分钟不能超过10次,否则会IP限制
os.system("scrapy crawl BookInfoSpider")
