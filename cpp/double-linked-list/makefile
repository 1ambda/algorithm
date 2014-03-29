CXX = g++
CXXFLAGS = -std=c++11 -Wall -Wextra
LINK = g++

SRCDIR = src
OBJDIR = obj

# Files
SOURCES = $(wildcard $(SRCDIR)/*.cpp)
OBJECTS = $(addprefix $(OBJDIR)/, $(notdir $(SOURCES:.cpp=.o)))
TARGET = main

# Compile
$(OBJDIR)/%.o: $(SRCDIR)/%.cpp
	$(CXX) -c $(CXXFLAGS) -o $@ $<

# Build rules
all: $(TARGET)
$(TARGET): $(OBJECTS)
	$(LINK) -o $(TARGET) $(OBJECTS)
clean:
	rm -f $(OBJECTS) $(TARGET) $(TARGET).exe.stackdump
