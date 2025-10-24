'''
UPDATED VERSION - 2024
Haseeb Younis
Please make sure you use the PEP guide for naming conventions in your submission
- detailed guide: https://www.python.org/dev/peps/pep-0008/
- some examples: https://stackoverflow.com/questions/159720/what-is-the-naming-convention-in-python-for-variable-and-function-names

This assignment is heavily based on
A Currency Converter GUI Program - Python PyQt6 Desktop Application Development Tutorial

- Layout
    - I would suggest QGridLayout
    - Use a QCalendarWidget which you will get from Zetcode tutorial called "Widgets" 
'''

# TODO: Delete the above, and include in a comment your name and student number
# TODO: Remember to fully comment your code


# standard imports
import sys
from PyQt6.QtCore import QDate
from PyQt6.QtWidgets import QLabel, QComboBox, QCalendarWidget, QDialog, QApplication, QGridLayout, QSpinBox
from PyQt6 import QtCore
from decimal import Decimal


class StockTradeProfitCalculator(QDialog):
    '''
    Provides the following functionality:

    - Allows the selection of the stock to be purchased
    - Allows the selection of the quantity to be purchased
    - Allows the selection of the purchase date
    - Displays the purchase total
    - Allows the selection of the sell date
    - Displays the sell total
    - Displays the profit total
    - Additional functionality

    '''

    def __init__(self):
        '''
        This method requires substantial updates
        Each of the widgets should be suitably initalized and laid out
        '''
        super().__init__()

        # setting up dictionary of stocks
        self.data = self.make_data()
        # sorting the dictionary of stocks by the keys. The keys at the high level are dates, so we are sorting by date
        self.stocks = sorted(self.data.keys())

        # the following 2 lines of code are for debugging purposee and show you how to access the self.data to get dates and prices
        # print all the dates and close prices for BTC
        print("all the dates and close prices for BTC", self.data['BTC'])
        # print the close price for BTC on 04/29/2013
        print("the close price for BTC on 04/29/2013", self.data['BTC'][QDate(2013, 4, 29)])

        # The data in the file is in the following range
        #  first date in dataset - 29th Apr 2013
        #  last date in dataset - 6th Jul 2021
        # When the calendars load we want to ensure that the default dates selected are within the date range above
        #  we can do this by setting variables to store suitable default values for sellCalendar and buyCalendar.
        self.sellCalendarDefaultDate = sorted(self.data['BTC'].keys())[-1]  # Accessing the last element of a python list is explained with method 2 on https://www.geeksforgeeks.org/python-how-to-get-the-last-element-of-list/
        print("self.sellCalendarStartDate", self.sellCalendarDefaultDate)
        # self.buyCalendarDefaultDate
        # print("self.buyCalendarStartDate", self.buyCalendarDefaultDate)

        # create QLabel for stock purchased

        # create QComboBox and populate it with a list of stocks

        # create CalendarWidgets for selection of purchase and sell dates

        # create QSpinBox to select stock quantity purchased

        # create QLabels to show the stock purchase total

        # create QLabels to show the stock sell total

        # create QLabels to show the stock profit total

        # initialize the layout - 6 rows to start

        # row 0 - stock selection

        # row 1 - quantity selection

        # row 2 - purchase date selection

        # row 3 - display purchase total

        # row 4 - sell date selection

        # row 5 - display sell total

        # row 6 - display sell total

        # set the calendar values
        # purchase: two weeks before most recent
        # sell: most recent

        # connecting signals to slots to that a change in one control updates the UI

        # set the window title
        # update the UI

    def updateUi(self):
        '''
        This requires substantial development
        Updates the Ui when control values are changed, should also be called when the app initializes
        :return:
        '''
        try:
            print("")
            # get selected dates from calendars

            # perform necessary calculations to calculate totals

            # update the label displaying totals
        except Exception as e:
            print(e)

################ YOU DO NOT HAVE TO EDIT CODE BELOW THIS POINT  ########################################################

    def make_data(self):
        '''
        This code is complete
        Data source is derived from https://www.kaggle.com/camnugent/sandp500/download but use the provided file to avoid confusion

        Converts a CSV file to a dictonary fo dictionaries like

            Stock   -> Date      -> Close
            AAL     -> 08/02/2013 -> 14.75
                    -> 11/02/2013 -> 14.46
                    ...
            AAPL    -> 08/02/2013 -> 67.85
                    -> 11/02/2013 -> 65.56

        Helpful tutorials to understand this
        - https://stackoverflow.com/questions/482410/how-do-i-convert-a-string-to-a-double-in-python
        - nested dictionaries https://stackoverflow.com/questions/16333296/how-do-you-create-nested-dict-in-python
        - https://www.tutorialspoint.com/python3/python_strings.htm
        :return: a dictionary of dictionaries
        '''
        file = open("./CryptoCoins_Prices/combined.csv", "r")  # open a CSV file for reading https://docs.python.org/3/library/functions.html#open
        data = {}  # empty data dictionary
        file_rows = []  # empty list of file rows
        # add rows to the file_rows list
        for row in file:
            file_rows.append(row.strip())  # https://www.geeksforgeeks.org/python-string-strip-2/
        print("len(file_rows):" + str(len(file_rows)))

        # get the column headings of the CSV file
        row0 = file_rows[0]
        line = row0.split(",")
        column_headings = line
        print(column_headings)

        # get the unique list of stocks from the CSV file
        non_unique_stocks = []
        file_rows_from_row1_to_end = file_rows[1:len(file_rows) - 1]
        for row in file_rows_from_row1_to_end:
            line = row.split(",")
            non_unique_stocks.append(line[6])
        stocks = self.unique(non_unique_stocks)
        print("len(stocks):" + str(len(stocks)))
        print("stocks:" + str(stocks))

        # build the base dictionary of stocks
        for stock in stocks:
            data[stock] = {}

        # build the dictionary of dictionaries
        for row in file_rows_from_row1_to_end:
            line = row.split(",")
            date = self.string_date_into_QDate(line[0])
            stock = line[6]
            close_price = line[4]
            # include error handling code if close price is incorrect
            data[stock][date] = float(close_price)
        print("len(data):", len(data))
        return data

    def string_date_into_QDate(self, date_String):
        '''
        This method is complete
        Converts a data in a string format like that in a CSV file to QDate Objects for use with QCalendarWidget
        :param date_String: data in a string format
        :return:
        '''
        date_list = date_String.split("-")
        date_QDate = QDate(int(date_list[0]), int(date_list[1]), int(date_list[2]))
        return date_QDate

    def unique(self, non_unique_list):
        '''
        This method is complete
        Converts a list of non-unique values into a list of unique values
        Developed from https://www.geeksforgeeks.org/python-get-unique-values-list/
        :param non_unique_list: a list of non-unique values
        :return: a list of unique values
        '''
        # intilize a null list
        unique_list = []

        # traverse for all elements
        for x in non_unique_list:
            # check if exists in unique_list or not
            if x not in unique_list:
                unique_list.append(x)
                # print list
        return unique_list

# This is complete
if __name__ == '__main__':
    app = QApplication(sys.argv)
    currency_converter = StockTradeProfitCalculator()
    currency_converter.show()
    sys.exit(app.exec())