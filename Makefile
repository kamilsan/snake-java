OUT_DIR=out
FLAGS=-d $(OUT_DIR)
PKG_DIR=com/snake
SRCS=$(PKG_DIR)/Game.java $(PKG_DIR)/Window.java $(PKG_DIR)/GameView.java \
$(PKG_DIR)/Drawable.java $(PKG_DIR)/Snake.java $(PKG_DIR)/SnakeView.java \
$(PKG_DIR)/Apple.java $(PKG_DIR)/AppleView.java $(PKG_DIR)/TextView.java

all: $(SRCS)
	javac $(FLAGS) $(SRCS)

run:
	java -cp $(OUT_DIR) com.snake.Game

docs:
	javadoc -d ./docs com.snake

clean:
	rm -rf $(OUT_DIR)/$(PKG_DIR)/*.class