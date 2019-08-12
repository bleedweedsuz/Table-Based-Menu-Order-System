-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Mar 06, 2016 at 02:00 AM
-- Server version: 5.5.42-37.1-log
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `suzworks_emenu`
--

-- --------------------------------------------------------

--
-- Table structure for table `food_item`
--

CREATE TABLE IF NOT EXISTS `food_item` (
  `foodID` int(11) NOT NULL AUTO_INCREMENT,
  `fName` text NOT NULL,
  `fPrice` double NOT NULL,
  `fDescription` text NOT NULL,
  `galleryID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`foodID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `food_item`
--

INSERT INTO `food_item` (`foodID`, `fName`, `fPrice`, `fDescription`, `galleryID`, `CID`) VALUES
(1, 'Espresso', 160, 'Our espresso blend has a full-bodied taste with elements of chocolate, berries and honey. It works brilliantly in milk-based espresso drinks, but it also tastes great as a pure espresso. The Espresso is made of Arabica beans of the highest quality, with a small amount of Robusta. We use slow-roasted beans for the highest quality.', 2, 1),
(2, 'Caffe Latte', 150, 'Our Caffe Latte is made with a single or double shot of espresso and creamy, steamed milk. For a bit of variation, let us season your latte with hazelnut or vanilla.', 3, 1),
(3, 'Coffee', 110, '2 million cups of coffee are drunk around the world every day. A considerable number of these cups are drunk in the form of filter coffee. At SugarcaneÂ´s restaurant, we always serve your coffee freshly ground and freshly brewed.', 4, 1),
(5, 'Hot Chocolate', 210, 'Classic Hot Chocolate Our Hot Chocolate always hits the right spot. We use chocolate of the highest quality. Orange Twist Hot chocolate with orange flavour; chocolate in a new way.', 6, 1),
(6, 'Ice Tea', 85, 'Deliciously cool on a sweltering day. Our restaurant offers a wide range of homemade iced teas. Try Lemon mint, Rooibos or Green Guava.', 7, 1),
(7, 'Smoothies', 165, 'Smoothies are both filling and healthy. Our smoothies are made of natural ingredients that give you a quick burst of energy and a tasty vitamin kick.', 8, 1),
(8, 'Frappe', 250, 'A cold delicacy based on our own frappe mix. Choose between Creamy Caramel, Mocca and Toasted Marshmallows.', 9, 1),
(9, 'Spinach Sweet Crisp Salad', 250, 'Baby spinach tossed with freshly sliced strawberries, oranges, red grapes, dried cranberries, green onions, creamy goat cheese and raisin pecan sweet crisps, drizzled with our homemade pomegranate vinaigrette. ', 10, 2),
(10, 'Chopped Salad', 270, 'All-natural chicken, applewood smoked bacon, avocado, bleu cheese, tomatoes and green onions tossed with iceberg and romaine lettuce and our sweet and spicy house vinaigrette.', 11, 2),
(11, 'Chicken Caesar Salad', 285, 'Broiled chicken breast, julienne sliced and served on a bed of romaine lettuce with croutons, onions, Parmesan cheese and Caesar dressing with grilled pita.', 12, 2),
(12, 'Tuna Salad', 340, '(Mixed with apples, dried cranberries and light mayo), baby field greens, avocado, carrots, cucumbers and honey balsamic vinaigrette', 13, 2),
(13, 'Southwest Salad', 250, 'Slaw mix, quinoa and black beans, cucumbers, corn and tomato salsa, pickled jalapenos and tortilla strips with avocado ranch dressing. ', 14, 2),
(14, 'Spinach Bacon Salad', 280, 'Almond slivers, tomatoes, bacon feta cheese over baby spinachâ€¦ with grilled pita and hot bacon dressing and topped with a few strawberries slices.', 15, 2),
(15, 'Shrimp Caesar Salad', 300, 'Shrimp, Romaine lettuce, lemon, grape tomatoes, roasted onions, black bean corn salsa,  Parmesan cheese and citrus chipotle dressing.', 16, 2),
(16, 'Apple Walnut Salad', 260, 'Apple,Walnut with romaine hearts, Avocado, Cranberry, crumbled blue cheese, golden raisins and honey balsamic vinaigrette.', 17, 2),
(17, 'Toasted Sesame Kale Salad', 250, 'Made with fresh kale, shredded carrots and toasted sesame seeds in a ginger soy dressing, this salad is low in calories and packed with fiber, iron, calcium, antioxidants and vitamins A, C and K', 18, 2),
(18, 'Classic Potted Shrimps', 350, 'Classic seafood starter is best made the day before and left in the fridge overnight - perfect for a special Sunday lunch', 19, 3),
(19, 'Marinated beetroot with goat cheese', 340, 'Earthy beetroot works perfectly with creamy grilled goat cheese for an easy but impressive starter.', 20, 3),
(20, 'Creamy spiced mussels ', 400, 'Expand your sea food horizons ', 21, 3),
(21, 'Cream of wild mushroom soup', 350, 'This rich and filling dish is the perfect way to use up end-of-season mushrooms on the cheap.', 22, 3),
(22, 'Creamy smoked salmon', 420, 'Creamy and low-fat are not words you often hear together, but that is exactly what you get with this freeze-ahead starter.', 23, 3),
(23, 'Hot, sour broth prawns', 400, 'This healthy starter takes just 15 min to make and is a cleansing way to kick a Chinses meal.', 24, 3),
(24, 'Halloumi stuffed peppers', 300, 'Full of wonderful flavours and textures, these vegetarian stuffed peppers make a great supper or dinner party starter.', 25, 3),
(25, 'Pea, mint and spring onion soup', 200, 'The Parmesan (tuiles) make this soup stylish enough for entertaining - but they take just a few minutes to make and is served with biscuits.', 26, 3),
(26, 'Salmon and prawns with dill and lime aloli', 450, 'For a simple, smart starter, just mix mayo with garlic, herbs and a sqyeeze of lime and serve with seafood and brown bread.', 27, 3),
(27, 'Smoked trout plate and pitta crisps', 330, 'Cheaper than smoked salmon, this smoked trout dip makes a smart party nibble or simple starter.', 28, 3),
(28, 'Sausage and bean one-pot', 280, 'Warm yourself up on a chilly night with this comforting one-pot, and avoid the washing -up too.', 29, 3),
(29, 'Chicken Wings', 450, 'Sticky chicken wings, served with sour cream and tossed in yor choice of BBQ or Louisiana Hot Sauce.', 30, 3),
(30, 'Swimmer Crab with rice', 300, 'Swimmer crab in comfort curry with coconut milk and Indian rice. It is prepared using crabmeat, onions, curry leaves, coconut milk, tamaring etc', 31, 4),
(31, 'Grilled lobsters with Vanilla Cream Sauce', 430, 'Smoky grilled lobster with a fragrant vanilla bean cream sauce with rice.', 32, 4),
(32, 'Japanese Grilled Chicken Meatballs', 270, 'Full-flavored chicken meatballs smothered in a sweet and salty glaze, best with fry rice.', 33, 4),
(33, 'Barbecue Ribs', 400, 'A full rack of ribs smothered in our signature BBQ sauce, with golden fries, coleslaw and corn on the cob.', 34, 4),
(34, 'Grilled rack of lamb with ratatouille', 450, 'This rack of lamb is served with a rustic ratatouille. The vegetables in ratatouille - which include courgette and aubergine - are often cooked in stages to add texture to final result. The rack of lamb can either be grilled or put on the barbecue, it will taste spectacular either way.', 35, 4),
(35, 'Welsh Lamb navarin', 500, 'Welsh Lamb, packed with flavor from caramelized garlic, herbs and anchovies. A navarin is a French stew, traditionally served in the spring with seasonal vegetables, and well-loved for its inclusion of turnips.', 36, 4),
(36, 'Lamb rump with wild garlic and pea puree', 450, 'Lamb rump is an underrated yet supremely tasty cut. It is served with a pea puree, anchovies and wild garlic.', 37, 4),
(37, 'Organic Pork chop', 500, 'This stunning pork chop is perfect for a barbecue, with marinated pork chop, vibrant red cabbage and juicy California prunes. Achiote seeds are used to make up to two more batches of achiote oil, which can be used in marinades, sauces and stews.', 38, 4),
(38, 'Lobster Masala', 450, 'An indulgent lobster, this elegant curry features lobster tails poached in a fragrant, spiced tomato sauce. Frying the whole spices first releases the maximum aroma into the oil for flavoring the rest of the dish.', 39, 4),
(39, 'Barbecued pork chop with apple ', 500, 'This magnificent barbecued pork chop is a wonderful combination of tastes and textures. Barbecued pork chops share the stage with tenderly cooked ribs coated with homemade barbecue sauce. Serve with endives and apple puree, as well as a good stash of napkins.', 40, 4),
(40, 'Mousse', 300, 'A mousse characteristically has a light and airy texture - but is is not restrained to the chocolate variety, mousse recipes can be savory or sweet.', 41, 5),
(41, 'Bitter chocolate ', 350, 'This lavish chocolate dessert manages to be both extravagant and natural at the same time. It features a few nifty components but all add up to a harmonious dessert bursting with bitter chocolate flavours and textures a chocoholic will love.', 42, 5),
(42, 'Black Forest Gateau', 480, 'Black forest gateau recipe includes a sumptuous flourless cake with a kirsch milk chocolate mousse and chocolate glaze. Why not hold your own seventies-inspired dinner party and serve this as a lavish dessert Ã¢â‚¬â€œ prawn cocktail would make a great starter and chicken Kiev could be your main.', 43, 5),
(43, 'Strawberry and cream panna cotta', 350, 'This strawberry and cream panna cotta recipe is a great way to usher in the British strawberry season. You can make these desserts in advance before serving at a summer barbecue or dinner party.', 44, 5),
(44, 'Madagascar-Pink pepper chocolate ', 500, 'It includes a trio of chocolate-laced goodies including a chocolate and rum biscuit, a cocoa crumble and a pink pepper-infused chocolate cream for a hit of spice.', 17, 5),
(45, 'Gascon mess', 410, 'It is serves in mess as layers in individual glasses but you could also fold the prunes, meringue and cream together as one large dish, if preferred.', 45, 5);

-- --------------------------------------------------------

--
-- Table structure for table `gallery_info`
--

CREATE TABLE IF NOT EXISTS `gallery_info` (
  `galleryID` int(11) NOT NULL AUTO_INCREMENT,
  `galleryName` text NOT NULL,
  PRIMARY KEY (`galleryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `gallery_info`
--

INSERT INTO `gallery_info` (`galleryID`, `galleryName`) VALUES
(1, 'Food Category Icons'),
(2, 'Espresso'),
(3, 'Caffe Latte'),
(4, 'Coffee'),
(6, 'Hot Chocolate'),
(7, 'Ice Tea'),
(8, 'Smoothies'),
(9, 'Frappe'),
(10, 'Spinach Sweet Crisp Salad'),
(11, 'Chopped Salad'),
(12, 'Chicken Caesar Salad'),
(13, 'Tuna Salad'),
(14, 'Southwest salad'),
(15, 'Spinach Bacon Salad'),
(16, 'Shrimp Caesar Salad'),
(17, 'Madgascar'),
(18, 'Toasted Sesame Kale Salad'),
(19, 'Classic Potted Shrimp'),
(20, 'Marinated beetroot'),
(21, 'Creamy mussels'),
(22, 'Cream of wild'),
(23, 'Creamy smoked salmon'),
(24, 'Hot sour broth'),
(25, 'Halloumi stuffed'),
(26, 'Pea mint'),
(27, 'Salmon and prawn'),
(28, 'Smoked trout'),
(29, 'Sausage and bean'),
(30, 'Chicken wings'),
(31, 'Swimmer crab'),
(32, 'Grilled lobster'),
(33, 'Japnese grilled chicken'),
(34, 'Barbecue ribs'),
(35, 'Grilled rack lamb'),
(36, 'Welsh lamb'),
(37, 'Lamb rumb garlic'),
(38, 'Organic pork'),
(39, 'Lobster Masala'),
(40, 'Barbecued pork'),
(41, 'Mousse'),
(42, 'Bitter Chocolate'),
(43, 'Black Forest'),
(44, 'Strawberry cream'),
(45, 'Gascon');

-- --------------------------------------------------------

--
-- Table structure for table `item_category`
--

CREATE TABLE IF NOT EXISTS `item_category` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `cName` text NOT NULL,
  `pID` int(11) NOT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `item_category`
--

INSERT INTO `item_category` (`CID`, `cName`, `pID`) VALUES
(1, 'Drinks', 1),
(2, 'Salads', 2),
(3, 'Starters', 3),
(4, 'Mains', 4),
(5, 'Desserts', 5);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `SN` int(11) NOT NULL AUTO_INCREMENT,
  `tableNO` int(11) NOT NULL,
  `foodID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `note` text NOT NULL,
  `time` datetime NOT NULL,
  `state` tinyint(1) NOT NULL,
  `token` text NOT NULL,
  `leave` int(11) NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`SN`, `tableNO`, `foodID`, `quantity`, `note`, `time`, `state`, `token`, `leave`, `Price`) VALUES
(1, 1, 2, 1, '', '2016-02-27 12:10:04', 1, 'T-20160227120841-9008594-1-1', 1, 150),
(2, 11, 1, 1, 'I wan to Plain', '2016-02-27 07:35:53', 1, 'T-20160227073410-6203729-2-3', 0, 160),
(3, 11, 1, 1, '', '2016-02-27 07:35:53', 1, 'T-20160227073410-6203729-2-2', 0, 160),
(4, 3, 1, 1, 'hgdhgxhgxhxhgxhgcjcgjyc', '2016-03-06 00:00:00', 0, 'T-20160305115024-2089251-4-5', 1, 160),
(5, 0, 1, 1, '', '2016-03-06 12:46:31', 1, 'T-20160306070521-3721400-31-32', 0, 160),
(6, 0, 2, 1, '', '2016-03-06 12:46:31', 1, 'T-20160306070521-3721400-31-31', 0, 150),
(7, 0, 9, 1, '', '2016-03-06 12:46:31', 1, 'T-20160306070521-3721400-31-30', 0, 250),
(8, 3, 40, 1, '', '2016-03-06 01:25:29', 1, 'T-20160305115024-2089251-4-6', 1, 300);

-- --------------------------------------------------------

--
-- Table structure for table `photo`
--

CREATE TABLE IF NOT EXISTS `photo` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `image` text NOT NULL,
  `galleryID` int(11) NOT NULL,
  PRIMARY KEY (`pID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=71 ;

--
-- Dumping data for table `photo`
--

INSERT INTO `photo` (`pID`, `image`, `galleryID`) VALUES
(1, 'gallery/16_02_26_10_18_42_91548drink.png', 1),
(2, 'gallery/16_02_26_10_18_48_18503salad.png', 1),
(3, 'gallery/16_02_26_10_18_55_37029starter.png', 1),
(4, 'gallery/16_02_26_10_19_02_97423main.png', 1),
(5, 'gallery/16_02_26_10_19_07_69653dessert.png', 1),
(8, 'gallery/16_02_26_12_37_10_84322espresso02.jpg', 2),
(12, 'gallery/16_02_26_12_46_41_60589caffe_latte1.jpg', 3),
(13, 'gallery/16_02_26_12_47_51_49850caffe_latte2.jpg', 3),
(16, 'gallery/16_02_26_12_59_07_41940coffee.jpg', 4),
(17, 'gallery/16_02_26_01_05_38_82737tea1.jpg', 5),
(18, 'gallery/16_02_26_01_11_34_45620hotechocolate1.jpg', 6),
(19, 'gallery/16_02_26_01_11_39_55126hotchocolate2.jpg', 6),
(20, 'gallery/16_02_26_01_21_00_22606icetea2.png', 7),
(24, 'gallery/16_02_26_01_28_48_63907icetea3.jpg', 7),
(25, 'gallery/16_02_26_01_34_04_46529yougartsmoothie1.jpg', 8),
(26, 'gallery/16_02_26_01_38_51_23631smoothie3.jpg', 8),
(27, 'gallery/16_02_26_01_53_49_89293frappe1.jpg', 9),
(28, 'gallery/16_02_26_01_53_55_15995frappe2.jpg', 9),
(29, 'gallery/16_02_26_02_10_45_29248spanichsalad2.jpg', 10),
(32, 'gallery/16_02_26_02_18_33_26259choppedsalad.jpg', 11),
(33, 'gallery/16_02_26_02_41_45_11051chickencaesarsalad.jpg', 12),
(34, 'gallery/16_02_26_02_44_27_30272tuscantunasalad1.jpg', 13),
(35, 'gallery/16_02_26_02_50_34_24158southwestsalad.jpg', 14),
(36, 'gallery/16_02_26_02_56_05_48394spanichbaconsalad1.jpg', 15),
(37, 'gallery/16_02_26_03_01_27_93298cayenneshrimp2.jpg', 16),
(40, 'gallery/16_02_26_03_25_16_22340toastedsesamekalesalad1.jpg', 18),
(43, 'gallery/16_03_05_09_04_09_65120shrimp.jpg', 19),
(44, 'gallery/16_03_05_09_22_09_76924marinatedt.jpg', 20),
(45, 'gallery/16_03_05_09_28_42_22061creamyspiced.jpg', 21),
(46, 'gallery/16_03_05_09_30_53_53001mushroomsoup.jpg', 22),
(47, 'gallery/16_03_05_09_33_20_31991creamysalmon.jpg', 23),
(48, 'gallery/16_03_05_09_36_15_71759prownsoup.jpg', 24),
(49, 'gallery/16_03_05_09_38_05_19011halloumi.jpg', 25),
(50, 'gallery/16_03_05_09_38_47_13616peamint.jpg', 26),
(51, 'gallery/16_03_05_09_45_31_54508salmonandprawns.jpg', 27),
(52, 'gallery/16_03_05_09_47_54_51695smokedtrout.jpg', 28),
(53, 'gallery/16_03_05_09_48_56_96441sausagebean1.png', 29),
(54, 'gallery/16_03_05_09_49_45_38902chickenwings.jpg', 30),
(55, 'gallery/16_03_05_10_00_29_28790crabdish.jpg', 31),
(56, 'gallery/16_03_05_10_01_18_42740lobseter.jpg', 32),
(57, 'gallery/16_03_05_10_02_22_46975japnesemeatballs.jpg', 33),
(58, 'gallery/16_03_05_10_03_11_81344barbecueribs.jpg', 34),
(59, 'gallery/16_03_05_10_04_16_46637grilled2.jpg', 35),
(60, 'gallery/16_03_05_10_05_02_74072welshlambnavarin.jpg', 36),
(61, 'gallery/16_03_05_10_06_15_46480lambrump.jpg', 37),
(62, 'gallery/16_03_05_10_06_34_27020organicporkchop.jpg', 38),
(63, 'gallery/16_03_05_10_19_32_17023lobstermasala.jpg', 39),
(64, 'gallery/16_03_05_10_20_31_59222barbecuedpork.jpg', 40),
(65, 'gallery/16_03_05_10_23_44_54062mousse.jpg', 41),
(66, 'gallery/16_03_05_10_24_12_28992bitterchocolate.jpg', 42),
(67, 'gallery/16_03_05_10_24_40_68422blackforest.jpg', 43),
(68, 'gallery/16_03_05_10_25_09_93771strawberrycream.jpg', 44),
(69, 'gallery/16_03_05_10_30_07_95321madagascar.jpg', 17),
(70, 'gallery/16_03_05_10_30_53_98705gascon.jpg', 45);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_detail`
--

CREATE TABLE IF NOT EXISTS `restaurant_detail` (
  `SN` int(11) NOT NULL AUTO_INCREMENT,
  `res_name` text NOT NULL,
  `res_info` text NOT NULL,
  `res_bestdish` text NOT NULL,
  `res_tutorial` text NOT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `restaurant_detail`
--

INSERT INTO `restaurant_detail` (`SN`, `res_name`, `res_info`, `res_bestdish`, `res_tutorial`) VALUES
(1, 'Sugarcane Restaurant', 'The Restaurant was opened in Pokhara, Nepal in July of 2011. today, the restaurant continued to prioritize freshly made multi cultural cuisines to cater for the individual and family needs. It is a stylish restaurant providing quality food, clean and superb dining atmosphere with wide space and good lighting effects too. Staffs working here has years of experience and performs their best to meet the requests and satisfy the wishes of each and every customers.', 'Tuna Salad', '(Ingredients:Cilantro, Diced Tomatoes, Red Onion,apples, dried cranberries, light mayo, Lemon Juice, Salt And Pepper, Tuna, baby field greens, avocado, carrots, cucumbers, honey balsamic vinaigrette)First off drain and rinse your tuna. Dice tomatoes and onion. Wash and remove the tops of the cilantro. Discard the stems. Put all of the previous ingredients in a bowl and squeeze lemon juice into bowl. Add salt and pepper. Mix it all together and eat.');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_table`
--

CREATE TABLE IF NOT EXISTS `restaurant_table` (
  `SN` int(11) NOT NULL AUTO_INCREMENT,
  `tableNO` int(11) NOT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `userdetail`
--

CREATE TABLE IF NOT EXISTS `userdetail` (
  `uID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` text NOT NULL,
  `userPassword` text NOT NULL,
  `userType` text NOT NULL,
  `uName` text NOT NULL,
  `uAddress` text NOT NULL,
  `uPhone` text NOT NULL,
  PRIMARY KEY (`uID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `userdetail`
--

INSERT INTO `userdetail` (`uID`, `userName`, `userPassword`, `userType`, `uName`, `uAddress`, `uPhone`) VALUES
(1, 'Saujan', 'saujan', 'Admin', 'Saujan Thapa', 'Nepal.Pokhara', '9806676868'),
(2, 'Sujan', 'sujan', 'Other', 'Sujan Thapa', 'Nepal, Pokhara', '9806619978');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
