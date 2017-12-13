/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : songmanage

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-12-07 22:24:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `song`
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` char(8) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `language` char(4) DEFAULT NULL,
  `category` char(4) DEFAULT NULL,
  `singer` varchar(50) DEFAULT NULL,
  `orderby` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `orderby` (`orderby`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('047f7fb7', '漫步人生路', '日文', '流行', '中岛美雪', '35');
INSERT INTO `song` VALUES ('049ec1e7', '像风一样', '中文', '流行', '薛之谦', '12');
INSERT INTO `song` VALUES ('0fcb35b5', '你还要我怎样', '中文', '流行', '薛之谦', '10');
INSERT INTO `song` VALUES ('21837332', 'You Raise Me Up', '英文', '通俗', 'Westlife', '30');
INSERT INTO `song` VALUES ('2b3d7bca', 'I Knew You Were Trouble', '英文', '流行', 'Taylor Swift', '25');
INSERT INTO `song` VALUES ('3c7866d4', 'Summer', '日文', '纯音乐', '久石让', '33');
INSERT INTO `song` VALUES ('41fd27ac', '演员', '中文', '流行', '薛之谦', '11');
INSERT INTO `song` VALUES ('452e5852', '3', '3', '3', '3', '9');
INSERT INTO `song` VALUES ('4e28681f', 'Blank Space', '英文', '流行', 'Taylor Swift', '26');
INSERT INTO `song` VALUES ('52c60082', 'Swear it Again', '英文', '流行', 'Westlife', '29');
INSERT INTO `song` VALUES ('53f0fcec', '回到那个夏天', '日文', '纯音乐', '久石让', '34');
INSERT INTO `song` VALUES ('5f2277f6', '荷塘月色', '中文', '流行', '凤凰传奇', '23');
INSERT INTO `song` VALUES ('60b3eb10', 'safe and sound', '英文', '流行', 'Wasted Penguinz', '28');
INSERT INTO `song` VALUES ('6c4f3bcc', '素颜', '中文', '流行', '许嵩', '19');
INSERT INTO `song` VALUES ('76d1a0f6', '月亮之上', '中文', '通俗', '凤凰传奇', '21');
INSERT INTO `song` VALUES ('794cb45e', 'testswing', '英文', '通俗', '小明', '5');
INSERT INTO `song` VALUES ('7f813de7', 'safe and sound', '英文', '流行', 'Taylor Swift', '27');
INSERT INTO `song` VALUES ('9126005b', '永远同在', '日文', '流行', '宗次郎', '31');
INSERT INTO `song` VALUES ('9a985ea3', '笑红尘', '中文', '古风', '陈淑桦', '16');
INSERT INTO `song` VALUES ('a7b06437', '习惯孤独', '日文', '流行', '中岛美雪', '36');
INSERT INTO `song` VALUES ('aac08ca4', '2', '中文', '流行', '1', '8');
INSERT INTO `song` VALUES ('abbb5f72', '庐州月', '中文', '古风', '许嵩', '17');
INSERT INTO `song` VALUES ('bf6d3d5b', 'testadd', '英文', '乡村', 'test', '4');
INSERT INTO `song` VALUES ('c667b3a2', '最炫民族风', '中文', '通俗', '凤凰传奇', '22');
INSERT INTO `song` VALUES ('cd17d9fc', '丑八怪', '中文', '流行', '薛之谦', '13');
INSERT INTO `song` VALUES ('db372b16', '自由飞翔', '中文', '通俗', '凤凰传奇', '20');
INSERT INTO `song` VALUES ('e691b6eb', '清明雨上', '中文', '古风', '许嵩', '15');
INSERT INTO `song` VALUES ('eaf49c11', 'We Are Never Ever Getting Back Together', '英文', '流行', 'Taylor Swift', '24');
INSERT INTO `song` VALUES ('ecec0b25', '月光下的云海', '日文', '纯音乐', '久石让', '32');
INSERT INTO `song` VALUES ('efb76d8b', '城府', '中文', '流行', '许嵩', '18');
INSERT INTO `song` VALUES ('f17e1e1e', '初学者', '中文', '流行', '薛之谦', '14');
INSERT INTO `song` VALUES ('fddcfdb9', '口红', '日文', '流行', '中岛美雪', '37');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(8) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int(11) DEFAULT '1',
  `orderby` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `orderby` (`orderby`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('05ded731', '李四', '1234', '1', '9');
INSERT INTO `user` VALUES ('09ce8c1e', '萨芬', '123', '1', '19');
INSERT INTO `user` VALUES ('1', 'admin', '123', '2', '1');
INSERT INTO `user` VALUES ('1a8c9b0b', 'test', '123', '1', '10');
INSERT INTO `user` VALUES ('2', '张三', '123', '1', '2');
INSERT INTO `user` VALUES ('2c64bd5d', 'add', '123', '1', '14');
INSERT INTO `user` VALUES ('3', 'testadd', 'testedit', '2', '3');
INSERT INTO `user` VALUES ('4410b7f8', '三分到手的', '123', '1', '24');
INSERT INTO `user` VALUES ('530717cb', '阿三', '123', '1', '16');
INSERT INTO `user` VALUES ('6510e83a', '沙发上', '123', '1', '21');
INSERT INTO `user` VALUES ('66cbae42', 'add2', '123', '1', '20');
INSERT INTO `user` VALUES ('9c98afd3', '岁的法国的', '123', '1', '22');
INSERT INTO `user` VALUES ('a355caf4', '士大夫', '123', '1', '25');
INSERT INTO `user` VALUES ('adebe9ad', '是否', '123', '1', '29');
INSERT INTO `user` VALUES ('beaed80f', '阿斯弗', '123', '1', '26');
INSERT INTO `user` VALUES ('d0dfa861', '是否是否', '123', '1', '30');
INSERT INTO `user` VALUES ('e1851f69', '1312', '123', '1', '28');
INSERT INTO `user` VALUES ('f502863a', 'test2', '123', '1', '11');
INSERT INTO `user` VALUES ('f5fdc21e', '发射点', '123', '1', '23');
