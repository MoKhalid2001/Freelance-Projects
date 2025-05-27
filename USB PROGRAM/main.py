import sys
import os
import psutil
import subprocess
from PyQt5.QtWidgets import (
    QApplication, QWidget, QVBoxLayout, QLabel,
    QListWidget, QPushButton, QHBoxLayout, QTextEdit, 
    QMessageBox, QTreeView, QFileSystemModel, QSplitter, 
    QStackedWidget
)
from PyQt5.QtGui import QFont
from PyQt5.QtCore import Qt, QThread, pyqtSignal


def get_connected_usb_drives():
    """Retrieve a list of all connected USB drives."""
    usb_drives = []
    for partition in psutil.disk_partitions(all=False):
        if 'removable' in partition.opts or 'media' in partition.device.lower():
            usb_drives.append(partition)
    return usb_drives


class WindowsDefenderScanThread(QThread):
    """Thread to perform Windows Defender scans in the background."""
    status_update = pyqtSignal(str)
    scan_finished = pyqtSignal()

    def __init__(self, drive_path):
        super().__init__()
        self.drive_path = drive_path

    def run(self):
        self.status_update.emit(f"Initiating Windows Defender scan on {self.drive_path}...")
        try:
            scan_command = [
                'powershell',
                '-Command',
                f"Start-MpScan -ScanPath '{self.drive_path}' -ScanType QuickScan"
            ]
            scan_result = subprocess.run(scan_command, capture_output=True, text=True, shell=True)

            if scan_result.stdout:
                self.status_update.emit("Scan output:")
                self.status_update.emit(scan_result.stdout.strip())

            if scan_result.stderr:
                self.status_update.emit("Scan error:")
                self.status_update.emit(scan_result.stderr.strip())

            success_message = "Scan completed successfully." if scan_result.returncode == 0 else f"Scan failed with code {scan_result.returncode}."
            self.status_update.emit(success_message)

        except Exception as error:
            self.status_update.emit(f"Scan error: {error}")

        self.scan_finished.emit()


class USBDriveExplorer(QWidget):
    """Widget to explore files on USB drives."""
    def __init__(self):
        super().__init__()
        self.setup_ui()
        
    def setup_ui(self):
        main_layout = QVBoxLayout()
        self._create_header(main_layout)
        self._setup_file_browser(main_layout)
        self.setLayout(main_layout)
        
    def _create_header(self, parent_layout):
        header_layout = QHBoxLayout()
        
        # Drive icon
        drive_icon_label = QLabel("💾")
        drive_icon_label.setFont(QFont("Segoe UI", 14))
        header_layout.addWidget(drive_icon_label)
        
        # Header text
        self.header_label = QLabel("Drive Contents")
        self.header_label.setFont(QFont("Segoe UI", 12, QFont.Bold))
        header_layout.addWidget(self.header_label)
        header_layout.addStretch()
        
        parent_layout.addLayout(header_layout)
        
    def _setup_file_browser(self, parent_layout):
        # File system setup
        self.file_system_model = QFileSystemModel()
        self.file_system_model.setRootPath("")
        
        # Tree view configuration
        self.file_tree_view = QTreeView()
        self.file_tree_view.setModel(self.file_system_model)
        self.file_tree_view.setAnimated(False)
        self.file_tree_view.setIndentation(20)
        self.file_tree_view.setSortingEnabled(True)
        self.file_tree_view.setColumnWidth(0, 250)
        self.file_tree_view.setHeaderHidden(True)
        
        # Hide size, type, and date columns
        for col in range(1, 4):
            self.file_tree_view.hideColumn(col)
        
        # Placeholder message
        self.placeholder_label = QLabel("Please select a USB drive")
        self.placeholder_label.setAlignment(Qt.AlignCenter)
        self.placeholder_label.setStyleSheet("color: #666; font-size: 11pt;")
        
        # View stack
        self.view_stack = QStackedWidget()
        self.view_stack.addWidget(self.placeholder_label)
        self.view_stack.addWidget(self.file_tree_view)
        parent_layout.addWidget(self.view_stack)
        
    def display_drive_contents(self, drive_path):
        """Show the contents of the selected drive."""
        if drive_path and os.path.exists(drive_path):
            self.file_system_model.setRootPath(drive_path)
            self.file_tree_view.setRootIndex(self.file_system_model.index(drive_path))
            self.header_label.setText(f"Drive Contents - {drive_path}")
            self.view_stack.setCurrentIndex(1)
        else:
            self.view_stack.setCurrentIndex(0)
            self.header_label.setText("Drive Contents")


class USBDriveManager(QWidget):
    """Main window for managing USB drives."""
    def __init__(self):
        super().__init__()
        self.window_title = "USB Drive Manager"
        self.window_size = (800, 600)
        self.setup_ui()
        self.apply_styles()
        self.update_drive_list()

    def setup_ui(self):
        self.setWindowTitle(self.window_title)
        self.setGeometry(200, 200, *self.window_size)
        
        main_layout = QVBoxLayout()
        self._create_drive_section(main_layout)
        self._create_file_explorer(main_layout)
        self.setLayout(main_layout)

    def _create_drive_section(self, parent_layout):
        drive_section_widget = QWidget()
        drive_section_layout = QVBoxLayout()

        # Drive list header
        header_label = QLabel("USB Drives")
        header_label.setFont(QFont("Segoe UI", 13, QFont.Bold))
        drive_section_layout.addWidget(header_label)

        # Drive list
        self.drive_list_widget = QListWidget()
        self.drive_list_widget.itemSelectionChanged.connect(self._on_drive_selection_change)
        drive_section_layout.addWidget(self.drive_list_widget)

        # Refresh button
        refresh_button = QPushButton("Refresh Drives")
        refresh_button.clicked.connect(self.update_drive_list)
        refresh_button.setObjectName("refreshBtn")
        drive_section_layout.addWidget(refresh_button)

        # Action buttons
        action_buttons_layout = QHBoxLayout()
        
        scan_button = QPushButton("Scan Drive")
        scan_button.setObjectName("scanBtn")
        scan_button.clicked.connect(self.scan_selected_drive)
        action_buttons_layout.addWidget(scan_button)

        clean_button = QPushButton("Clean Drive")
        clean_button.setObjectName("cleanBtn")
        clean_button.clicked.connect(self.clean_selected_drive)
        action_buttons_layout.addWidget(clean_button)

        format_button = QPushButton("Format Drive")
        format_button.setObjectName("formatBtn")
        format_button.clicked.connect(self.format_selected_drive)
        action_buttons_layout.addWidget(format_button)

        drive_section_layout.addLayout(action_buttons_layout)
        drive_section_widget.setLayout(drive_section_layout)

        # Add to main layout with splitter
        self.main_splitter = QSplitter(Qt.Vertical)
        self.main_splitter.addWidget(drive_section_widget)
        parent_layout.addWidget(self.main_splitter)

    def _create_file_explorer(self, parent_layout):
        self.drive_explorer = USBDriveExplorer()
        self.main_splitter.addWidget(self.drive_explorer)

        # Status log
        self.status_log_text_edit = QTextEdit()
        self.status_log_text_edit.setReadOnly(True)
        self.status_log_text_edit.setObjectName("logBox")
        self.status_log_text_edit.setMaximumHeight(150)
        self.main_splitter.addWidget(self.status_log_text_edit)

        # Set section sizes
        self.main_splitter.setSizes([200, 300, 100])

    def _on_drive_selection_change(self):
        selected_item = self.drive_list_widget.currentItem()
        if selected_item:
            drive_path = selected_item.text().split(" - ")[0]
            self.drive_explorer.display_drive_contents(drive_path)
            self.log_status_message(f"Selected drive: {drive_path}")

    def update_drive_list(self):
        """Refresh the list of connected USB drives."""
        self.drive_list_widget.clear()
        drives = get_connected_usb_drives()
        
        if not drives:
            self.drive_list_widget.addItem("No USB drives detected")
            return
            
        for drive in drives:
            self.drive_list_widget.addItem(f"{drive.device} - {drive.fstype}")

    def scan_selected_drive(self):
        """Perform a scan on the selected drive using Windows Defender."""
        if not (drive_path := self._get_selected_drive_path()):
            return

        self.log_status_message(f"Starting scan on {drive_path}")
        self.scanner_thread = WindowsDefenderScanThread(drive_path)
        self.scanner_thread.status_update.connect(self.log_status_message)
        self.scanner_thread.scan_finished.connect(lambda: self.log_status_message("Scan complete"))
        self.scanner_thread.start()

    def clean_selected_drive(self):
        """Remove potentially harmful files from the selected drive."""
        if not (drive_path := self._get_selected_drive_path()):
            return

        self.log_status_message(f"Checking {drive_path} for suspicious files...")
        dangerous_file_extensions = ['.exe', '.vbs', '.bat', '.scr', '.lnk', '.cmd']
        suspicious_files = []

        for root, _, files in os.walk(drive_path):
            for file in files:
                if any(file.lower().endswith(ext) for ext in dangerous_file_extensions):
                    suspicious_files.append(os.path.join(root, file))

        if not suspicious_files:
            self.log_status_message("No suspicious files found")
            return

        if self._confirm_file_deletion(len(suspicious_files)):
            self._delete_suspicious_files(suspicious_files)

    def format_selected_drive(self):
        """Format the selected USB drive."""
        if not (drive_path := self._get_selected_drive_path()):
            return

        if not self._confirm_drive_format(drive_path):
            self.log_status_message("Format cancelled")
            return

        self.log_status_message(f"Formatting {drive_path}")
        self._execute_format_command(drive_path)

    def _get_selected_drive_path(self):
        """Retrieve the path of the selected drive or show an error message."""
        if not self.drive_list_widget.currentItem():
            QMessageBox.warning(self, "No Drive Selected", "Please select a USB drive")
            return None
        return self.drive_list_widget.currentItem().text().split(" - ")[0]

    def _confirm_file_deletion(self, file_count):
        """Prompt the user to confirm deletion of suspicious files."""
        return QMessageBox.question(
            self, "Confirm Cleanup",
            f"Found {file_count} suspicious files. Delete them?",
            QMessageBox.Yes | QMessageBox.No
        ) == QMessageBox.Yes

    def _delete_suspicious_files(self, files):
        """Delete the specified list of suspicious files."""
        deleted_count = 0
        for file in files:
            try:
                os.remove(file)
                self.log_status_message(f"Removed: {file}")
                deleted_count += 1
            except Exception as e:
                self.log_status_message(f"Failed to remove {file}: {e}")
        self.log_status_message(f"Removed {deleted_count} files")

    def _confirm_drive_format(self, drive):
        """Prompt the user to confirm formatting of the drive."""
        return QMessageBox.question(
            self, "Confirm Format",
            f"Format drive {drive}?\nThis will erase all data!",
            QMessageBox.Yes | QMessageBox.No
        ) == QMessageBox.Yes

    def _execute_format_command(self, drive_path):
        """Run the command to format the drive."""
        try:
            drive_letter = drive_path.strip("\\").split(":")[0]
            format_script = f"""
            $drive = '{drive_letter}'
            $volume = Get-Volume -DriveLetter $drive -ErrorAction SilentlyContinue
            if ($volume -and $volume.DriveType -eq 'Removable') {{
                Format-Volume -DriveLetter $drive -FileSystem NTFS -NewFileSystemLabel 'USB_Drive' -Confirm:$false -Force
            }} else {{
                Write-Output "Invalid drive or not removable"
            }}
            """

            result = subprocess.run(
                ["powershell", "-Command", format_script],
                capture_output=True,
                text=True
            )

            if result.returncode == 0 and "Invalid" not in result.stdout:
                self.log_status_message(f"Successfully formatted {drive_letter}:")
            else:
                self.log_status_message("Format failed:")
            
            if result.stdout:
                self.log_status_message(result.stdout.strip())
            if result.stderr:
                self.log_status_message(result.stderr.strip())

        except Exception as e:
            self.log_status_message(f"Format error: {e}")

    def log_status_message(self, message):
        """Log a message to the status log."""
        self.status_log_text_edit.append(f"> {message}")

    def apply_styles(self):
        """Apply visual styles to the application."""
        self.setStyleSheet("""
            QWidget {
                background-color: #1e1e2f;
                color: #f0f0f0;
                font-family: 'Segoe UI';
                font-size: 11pt;
            }

            QListWidget, QTreeView {
                background-color: #2a2a3d;
                border: 1px solid #444;
                padding: 5px;
            }

            QTreeView::item {
                padding: 4px;
            }

            QTreeView::item:hover {
                background-color: #3a3a4d;
            }

            QTreeView::item:selected {
                background-color: #3d5a80;
            }

            QTextEdit#logBox {
                background-color: #2a2a3d;
                border: 1px solid #444;
                padding: 8px;
            }

            QPushButton {
                border-radius: 6px;
                padding: 8px 12px;
                font-weight: bold;
            }

            QPushButton#refreshBtn {
                background-color: #2d89ef;
                color: white;
            }
            QPushButton#refreshBtn:hover {
                background-color: #1b64c5;
            }

            QPushButton#scanBtn {
                background-color: #28a745;
                color: white;
            }
            QPushButton#scanBtn:hover {
                background-color: #218838;
            }

            QPushButton#cleanBtn {
                background-color: #ffc107;
                color: black;
            }
            QPushButton#cleanBtn:hover {
                background-color: #e0a800;
            }

            QPushButton#formatBtn {
                background-color: #dc3545;
                color: white;
            }
            QPushButton#formatBtn:hover {
                background-color: #c82333;
            }

            QSplitter::handle {
                background-color: #444;
            }

            QSplitter::handle:horizontal {
                width: 2px;
            }

            QSplitter::handle:vertical {
                height: 2px;
            }
        """)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    main_window = USBDriveManager()
    main_window.show()
    sys.exit(app.exec_())
